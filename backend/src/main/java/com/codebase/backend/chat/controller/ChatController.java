package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.ChatroomDTO;
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

    @PostMapping
    public ChatroomDTO createChatroom(@AuthenticationPrincipal Member user, @RequestParam String title) {
        System.out.println("user = " + user);
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
        System.out.println("user = " + user);
        List<Chatroom> chatrooms = chatService.getChatroomList(user);

        return chatrooms.stream().map(ChatroomDTO::from).collect(Collectors.toList());
    }
}
