package com.codebase.backend.chat.controller;

import com.codebase.backend.chat.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
public class StompChatController {


    @MessageMapping("/chats")
    @SendTo("/sub/chats")
    public ChatMessage handleMeaasge(
            @AuthenticationPrincipal Principal principal,
            @Payload Map<String, String> payload) {
        log.info("{} received", payload);

        return new ChatMessage(principal.getName(), payload.get("message"));
    }
}
