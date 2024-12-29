package com.codebase.backend.notification.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationRequest {
    private List<Long> notiIds;
}
