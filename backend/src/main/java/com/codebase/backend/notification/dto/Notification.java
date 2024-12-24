package com.codebase.backend.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {
    Long id;
    String content;
    Boolean isRead;
    LocalDateTime createdAt;

    // 알림 타입 :
    NotificationType type;
    int member;
}
