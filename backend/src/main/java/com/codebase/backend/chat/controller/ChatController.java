package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.ChatMessage;
import com.codebase.backend.chat.dto.ChatMessageDTO;
import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.ChatroomDTO;
import com.codebase.backend.chat.repository.ChatroomRepository;
import com.codebase.backend.chat.repository.MemberChatroomMappingRepository;
import com.codebase.backend.chat.service.ChatService;
import com.codebase.backend.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {

    private final ChatService chatService;
    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;

    @PostMapping
    public ChatroomDTO createChatroom(@AuthenticationPrincipal Member user, @RequestParam(value = "title") String title) {
        Chatroom chatroom = chatService.createChatroom(user, title);
        int memberCount = memberChatroomMappingRepository.countMemberByChatroomId(chatroom.getId());
        return ChatroomDTO.from(chatroom, memberCount);
    }

    @GetMapping("/exit/{chatroomId}")
    public void exitChatroom(@AuthenticationPrincipal Member user, @PathVariable int chatroomId) {
        chatService.exitChatroom(user, chatroomId);
    }

    @PostMapping("/{chatroomId}")
    public ChatroomDTO joinChatroom(@PathVariable int chatroomId, @RequestParam(value = "memberMail") String memberMail) {
        Chatroom joinedChatroom =chatService.joinChatroom(memberMail, chatroomId);
        return ChatroomDTO.from(joinedChatroom, memberChatroomMappingRepository.countMemberByChatroomId(joinedChatroom.getId()));
    }

    @DeleteMapping("/{chatroomId}")
    public Boolean leaveChatroom(@AuthenticationPrincipal Member user, @PathVariable int chatroomId) {
        return chatService.leaveChatroom(user, chatroomId);
    }

    @GetMapping
    public List<ChatroomDTO> getChatrooms(@AuthenticationPrincipal Member user) {
        List<Chatroom> chatroomList = chatService.getChatroomList(user);
        return chatroomList.stream().map(
                chatroom -> ChatroomDTO.from(chatroom, memberChatroomMappingRepository.countMemberByChatroomId(chatroom.getId()))
        ).collect(Collectors.toList());
    }

    @GetMapping("/{chatroomId}/messages")
    public List<ChatMessageDTO> getMessages(@PathVariable int chatroomId) {
        List<ChatMessage> messages = chatService.getMessageList(chatroomId);
        Chatroom chatroom = chatroomRepository.findById(chatroomId);

        return messages.stream().map(
                message -> ChatMessageDTO.from(message, chatroom.getTitle())
        ).collect(Collectors.toList());
    }

    @GetMapping("/find/{username}")
    public ChatroomDTO findChatroom(
            @AuthenticationPrincipal Member user,
            @PathVariable("username") String username
    ) {
        Chatroom chatroom = chatService.findChatroom(user, username);
        return ChatroomDTO.from(chatroom, 2);
    }
}
