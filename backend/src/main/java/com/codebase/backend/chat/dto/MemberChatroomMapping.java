package com.codebase.backend.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberChatroomMapping {
    int id;
    int member;
    int chatroom;

    LocalDateTime lastCheckedAt;
}
