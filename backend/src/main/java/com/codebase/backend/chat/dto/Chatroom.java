package com.codebase.backend.chat.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {
    Integer id;
    LocalDateTime createdDate;
    String title;
    Boolean DM;

    Boolean hasNewMessage;
}
