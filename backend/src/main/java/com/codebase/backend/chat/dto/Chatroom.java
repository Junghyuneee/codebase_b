package com.codebase.backend.chat.dto;

import com.codebase.backend.member.dto.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {
    Integer id;
    LocalDate createdDate;
    String title;
}
