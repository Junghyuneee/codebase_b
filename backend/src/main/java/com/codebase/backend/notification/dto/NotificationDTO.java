package com.codebase.backend.notification.dto;

public record NotificationDTO (
        Long id,
        String content,
        Boolean isRead,
        String createdAt,
        String type
){

    public static NotificationDTO from(Notification notification) {
        return new NotificationDTO(
                notification.getId(),
                notification.getContent(),
                notification.getIsRead(),
                notification.getCreatedAt().toString(),
                notification.getType().toString()
        );
    }
}


