package com.codebase.backend.chat.dto;

import java.time.LocalDate;

public record ChatroomDTO(
        int id,
        String title
//        LocalDate createDate
) {

    public static ChatroomDTO from(Chatroom chatroom) {
        return new ChatroomDTO(chatroom.getId(),
                chatroom.getTitle()
//                chatroom.getCreatedDate()
        );
    }
}
