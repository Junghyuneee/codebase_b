package com.codebase.backend.chat.service;

import com.codebase.backend.chat.dto.*;
import com.codebase.backend.chat.repository.ChatroomRepository;
import com.codebase.backend.chat.repository.MessageRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.chat.repository.MemberChatroomMappingRepository;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.member.service.MemberService;
import com.codebase.backend.notification.dto.NotificationType;
import com.codebase.backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;
    private final MemberService memberService;
    private final NotificationService notificationService;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
                .title(title)
                .createdDate(LocalDateTime.now())
                .build();

        chatroom = chatroomRepository.save(chatroom);

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member.getId())
                .chatroom(chatroom.getId())
                .lastCheckedAt(LocalDateTime.now())
                .build();

        memberChatroomMappingRepository.save(memberChatroomMapping);

        return chatroom;
    }

    public void exitChatroom(Member member, Integer currentChatroomId) {
        if (currentChatroomId != null) {
            memberChatroomMappingRepository.updateLastCheckedByMemberIdAndChatroomId(member.getId(), currentChatroomId);
        }
    }

    public Chatroom joinChatroom(String email, Integer newChatroomId) {
        Member newMember = memberRepository.findByEmail(email);
        Chatroom chatroom = chatroomRepository.findById(newChatroomId);

        if (memberChatroomMappingRepository.existsByMemberIdAndChatroomId(newMember.getId(), newChatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return chatroom;
        }

        if (chatroom.getDM()) {
            List<Integer> memberIdList = memberChatroomMappingRepository.findByChatroomId(newChatroomId).stream().map(MemberChatroomMapping::getMember).toList();
            List<Member> memberList = new ArrayList<>(memberIdList.stream().map(memberService::getMemberById).toList());
            memberList.add(newMember);

            Chatroom newChatroom = Chatroom.builder()
                    .title(memberList.stream().map(Member::getName).collect(Collectors.joining(", ")))
                    .createdDate(LocalDateTime.now())
                    .build();

            newChatroom = chatroomRepository.save(newChatroom);
            for (Member member : memberList) {
                memberChatroomMappingRepository.save(MemberChatroomMapping.builder()
                        .member(member.getId())
                        .chatroom(newChatroom.getId())
                        .lastCheckedAt(LocalDateTime.now())
                        .build());
            }

            return newChatroom;
        } else {
            MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                    .member(newMember.getId())
                    .chatroom(chatroom.getId())
                    .lastCheckedAt(LocalDateTime.now())
                    .build();

            memberChatroomMappingRepository.save(memberChatroomMapping);

            if(memberChatroomMappingRepository.countMemberByChatroomId(chatroom.getId()) == 2){
                chatroom.setDM(true);
                chatroom.setTitle(chatroom.getTitle() + ", " + newMember.getName());
                chatroomRepository.setDM(chatroom);
            }

            notificationService.save(String.valueOf(NotificationType.MESSAGE), "채팅방에 초대되었습니다. : " + chatroom.getTitle(), newMember.getId());

            return chatroom;
        }
    }

    public Chatroom inviteMembers(int chatroomId, List<String> memberNames) {
        List<Member> members = memberRepository.findByMemberNames(memberNames);
        Chatroom chatroom = chatroomRepository.findById(chatroomId);

        List<MemberChatroomMapping> mappings = new ArrayList<>();

        for(Member member: members){
            if(!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)){
                MemberChatroomMapping mapping = MemberChatroomMapping.builder()
                        .member(member.getId())
                        .chatroom(chatroomId)
                        .lastCheckedAt(LocalDateTime.now())
                        .build();
                mappings.add(mapping);
            }
        }

        memberChatroomMappingRepository.saveAll(mappings);

        return chatroom;
    }

    public Boolean leaveChatroom(Member member, int chatroomId) {
        if (!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("참여하지 않은 방입니다.");
            return false;
        }

        memberChatroomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);
        List<MemberChatroomMapping> memberChatroomMappings = memberChatroomMappingRepository.findByChatroomId(chatroomId);

        if (memberChatroomMappings.isEmpty()) {
            chatroomRepository.deleteById(chatroomId);
        }

        return true;
    }

    public List<Chatroom> getChatroomList(Member member) {
        List<MemberChatroomMapping> memberChatroomMappingRepositoryList = memberChatroomMappingRepository.findAllByMemberId(member.getId());

        return memberChatroomMappingRepositoryList.stream().map(
                        memberChatroomMapping -> {
                            Chatroom chatroom = chatroomRepository.findById(memberChatroomMapping.getChatroom());
                            chatroom.setHasNewMessage(
                                    messageRepository.existsByChatroomIdAndCreatedAtAfter(chatroom.getId(), memberChatroomMapping.getLastCheckedAt()));
                            return chatroom;
                        })
                .toList();
    }

    public ChatMessageDTO saveMessage(String authorization, String payload, int chatroomId) {

        // Parsing
        JSONObject jsonObject = (JSONObject) JSONValue.parse(payload);
        String text = (String) jsonObject.get("message");

        authorization = authorization.substring(7);
        String username = jwtService.getUsername(authorization);

        // Find Entity
        Chatroom chatroom = chatroomRepository.findById(chatroomId);
        Member member = memberRepository.findByEmail(username);

        ChatMessage message = ChatMessage.builder()
                .text(text)
                .room_id(chatroom.getId())
                .user_id(member.getId())
                .sender(member.getName())
                .senderMail(member.getEmail())
                .timestamp(LocalDateTime.now())
                .build();
        message = messageRepository.save(message);

        return ChatMessageDTO.from(message, chatroom.getTitle());
    }

    public List<ChatMessage> getMessageList(int chatroomId) {
        return messageRepository.findAllByChatroomId(chatroomId);
    }

    public Chatroom findChatroom(Member currentUser, String username) {
        Member dmUser = memberService.getMemberByName(username);
        if (dmUser == null) {
            throw new UsernameNotFoundException(username);
        }
        Integer chatroomId = memberChatroomMappingRepository.findByTwoMemberId(currentUser.getId(), dmUser.getId());
        if (chatroomId == null) {
            Chatroom chatroom = createChatroom(currentUser, currentUser.getName());
            joinChatroom(dmUser.getEmail(), chatroom.getId());
            return chatroom;
        } else {
            return chatroomRepository.findById(chatroomId);
        }
    }

    public void leaveAllChatroom(Member member) {
        List<Chatroom> chatroomList = getChatroomList(member);
        for (Chatroom chatroom : chatroomList) {
            leaveChatroom(member, chatroom.getId());
        }
    }
}
