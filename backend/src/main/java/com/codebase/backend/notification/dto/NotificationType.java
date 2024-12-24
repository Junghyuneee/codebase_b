package com.codebase.backend.notification.dto;

import lombok.Getter;

@Getter
public enum   NotificationType {
    MESSAGE(0),
    COMMENT(1),
    LIKES(2);

    private final int code;

    NotificationType(int code){
        this.code = code;
    }

//    public static NotificationType fromCode(int code){
//        for (NotificationType type : NotificationType.values()) {
//            if(type.getCode() == code){
//                return type;
//            }
//        }
//
//        throw new IllegalArgumentException("Invalid Notification Type: " + code);
//    }
}
