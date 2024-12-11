package com.codebase.backend.chat.dto;

public record ChatroomDTO(
        int id,
        String title,
        int memberCount,
        Boolean hasNewMessage
) {

    public static ChatroomDTO from(Chatroom chatroom, int memberCount) {
        return new ChatroomDTO(chatroom.getId(),
                chatroom.getTitle(),
                memberCount,
                chatroom.getHasNewMessage()
        );
    }
}
