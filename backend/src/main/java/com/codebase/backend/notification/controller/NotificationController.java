package com.codebase.backend.notification.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.notification.dto.Notification;
import com.codebase.backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/noti")
public class NotificationController {
    private final NotificationService notificationService;

    // 알림 목록 불러오기
    @GetMapping
    public ResponseEntity<List<Notification>> notification(@AuthenticationPrincipal Member member) {
        List<Notification> notifications = notificationService.findAll(member.getId());
        return ResponseEntity.ok(notifications);
    }

    // 특정 알림 읽음 처리
    @PostMapping("/read")
    public ResponseEntity<Void> read(@RequestBody List<Long> notiIds) {
        if (notificationService.readNotification(notiIds)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 알림 삭제
    @DeleteMapping("/delete/{notiId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "notiId") Long notiId) {
        if (notificationService.deleteNotification(notiId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
