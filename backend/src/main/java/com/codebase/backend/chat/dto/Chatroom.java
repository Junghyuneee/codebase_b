package com.codebase.backend.chat.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {
    Integer id;
    LocalDate createdDate;
    String title;

    Boolean hasNewMessage;
}
