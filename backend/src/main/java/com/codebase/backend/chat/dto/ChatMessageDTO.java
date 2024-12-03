package com.codebase.backend.chat.dto;

import java.time.LocalDateTime;

public record ChatMessageDTO(
        Long id,
        String message,
        LocalDateTime timestamp,
        String sender,
        String senderMail,
        String room

) {
    public static ChatMessageDTO from(ChatMessage message, String room) {
        return new ChatMessageDTO(
                message.getId(),
                message.getText(),
                message.getTimestamp(),
                message.getSender(),
                message.getSenderMail(),
                room
        );
    }
}
