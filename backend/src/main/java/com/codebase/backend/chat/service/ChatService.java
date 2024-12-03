package com.codebase.backend.chat.service;

import com.codebase.backend.chat.dto.ChatMessage;
import com.codebase.backend.chat.dto.ChatMessageDTO;
import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.MemberChatroomMapping;
import com.codebase.backend.chat.repository.ChatroomRepository;
import com.codebase.backend.chat.repository.MessageRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.chat.repository.MemberChatroomMappingRepository;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
                .title(title)
                .createdDate(LocalDate.now())
                .build();

        chatroom = chatroomRepository.save(chatroom);

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member.getId())
                .chatroom(chatroom.getId())
                .build();

        memberChatroomMappingRepository.save(memberChatroomMapping);

        return chatroom;
    }


    public Boolean joinChatroom(Member member, int chatroomId) {
        if (memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return false;
        }

        Chatroom chatroom = chatroomRepository.findById(chatroomId);

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member.getId())
                .chatroom(chatroom.getId())
                .build();

        memberChatroomMappingRepository.save(memberChatroomMapping);

        return true;
    }

    public Boolean leaveChatroom(Member member, int chatroomId) {
        if (!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("참여하지 않은 방입니다.");
            return false;
        }

        memberChatroomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);

        return true;
    }

    public List<Chatroom> getChatroomList(Member member) {
        List<Integer> chatroomIds = memberChatroomMappingRepository.findChatroomIdsByMemberId(member.getId());

        if (chatroomIds == null || chatroomIds.isEmpty()) {
            return Collections.emptyList();
        }

        return chatroomRepository.findAllByIds(chatroomIds);
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
                .timestamp(LocalDateTime.now())
                .build();
        message = messageRepository.save(message);

        return ChatMessageDTO.from(message, member.getName(), chatroom.getTitle());
    }

    public List<ChatMessage> getMessageList(int chatroomId) {
        return messageRepository.findAllByChatroomId(chatroomId);
    }
}
