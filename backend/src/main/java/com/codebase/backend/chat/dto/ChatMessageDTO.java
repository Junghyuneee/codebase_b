package com.codebase.backend.chat.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record ChatMessageDTO(
        Long id,
        String message,
        LocalDateTime timestamp,
        String sender,
        String room

) {
    public static ChatMessageDTO from(ChatMessage message, String sender, String room) {
        return new ChatMessageDTO(
                message.getId(),
                message.getText(),
                message.getTimestamp(),
                sender, room
        );
    }
}
