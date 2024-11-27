package com.codebase.backend.chat.service;

import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.MemberChatroomMapping;
import com.codebase.backend.chat.repository.ChatroomRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.chat.repository.MemberChatroomMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
                .title(title)
                .createdDate(LocalDate.now())
                .build();

        chatroom = chatroomRepository.save(chatroom);

        System.out.println("chatroom.getId() = " + chatroom.getId());

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member.getId())
                .chatroom(chatroom.getId())
                .build();

        memberChatroomMapping = memberChatroomMappingRepository.save(memberChatroomMapping);

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

        memberChatroomMapping = memberChatroomMappingRepository.save(memberChatroomMapping);

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
}
