package com.codebase.backend.chat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {

    long id;
    String text;
    LocalDateTime timestamp;

    int user_id;
    int room_id;
}
