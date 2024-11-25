package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.service.ChatService;
import com.codebase.backend.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("chats")
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public Chatroom createChatroom(@AuthenticationPrincipal Member user) {
        return chatService.createChatroom(user);
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
    public List<Chatroom> getChatrooms(@AuthenticationPrincipal Member user) {
        return chatService.getChatroomList(user);
    }
}
