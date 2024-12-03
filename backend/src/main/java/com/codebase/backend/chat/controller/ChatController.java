package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.ChatMessage;
import com.codebase.backend.chat.dto.ChatMessageDTO;
import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.ChatroomDTO;
import com.codebase.backend.chat.repository.ChatroomRepository;
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

    @PostMapping
    public ChatroomDTO createChatroom(@AuthenticationPrincipal Member user, @RequestParam String title) {
        Chatroom chatroom = chatService.createChatroom(user, title);
        return ChatroomDTO.from(chatroom);
    }

    @PostMapping("/{chatroomId}")
    public Boolean joinChatroom(@AuthenticationPrincipal Member user, @PathVariable int chatroomId) {
        return chatService.joinChatroom(user, chatroomId);
    }

    @DeleteMapping("/{chatroomId}")
    public Boolean leaveChatroom(@AuthenticationPrincipal Member user, @PathVariable int chatroomId) {
        return chatService.leaveChatroom(user, chatroomId);
    }

    @GetMapping
    public List<ChatroomDTO> getChatrooms(@AuthenticationPrincipal Member user) {
        List<Chatroom> chatrooms = chatService.getChatroomList(user);
        return chatrooms.stream().map(ChatroomDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/{chatroomId}/messages")
    public List<ChatMessageDTO> getMessages(@PathVariable int chatroomId) {
        List<ChatMessage> messages = chatService.getMessageList(chatroomId);
        Chatroom chatroom = chatroomRepository.findById(chatroomId);

        return messages.stream().map(
                message -> ChatMessageDTO.from(message, chatroom.getTitle())
        ).collect(Collectors.toList());
    }
}
