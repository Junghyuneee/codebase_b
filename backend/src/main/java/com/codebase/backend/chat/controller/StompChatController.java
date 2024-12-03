package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.ChatMessageDTO;
import com.codebase.backend.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final ChatService chatService;

    @MessageMapping("/chats/{chatroomId}")
    @SendTo("/sub/chats/{chatroomId}")
    public ChatMessageDTO handleMessage(
            @DestinationVariable int chatroomId, String payload, SimpMessageHeaderAccessor headerAccessor
    ) {
        String authorization = headerAccessor.getFirstNativeHeader("Authorization");
        if (authorization != null) {

            return chatService.saveMessage(authorization, payload, chatroomId);
        } else {
            System.out.println("Authorization header is missing.");
        }
        return null;
    }


}
