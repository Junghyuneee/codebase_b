package com.codebase.backend.chat.dto;

public record ChatMessageDTO(
        Long id,
        String message,
        String timestamp,
        String sender,
        String senderMail,
        String room

) {
    public static ChatMessageDTO from(ChatMessage message, String room) {
        return new ChatMessageDTO(
                message.getId(),
                message.getText(),
                message.getTimestamp().toString(),
                message.getSender(),
                message.getSenderMail(),
                room
        );
    }
}
