package com.codebase.backend.notification.service;

import com.codebase.backend.notification.dto.Notification;
import com.codebase.backend.notification.dto.NotificationType;
import com.codebase.backend.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // 알림 생성
    public Notification save(String type, String content, int memberId) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setType(NotificationType.valueOf(type));
        notification.setMember(memberId);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);

        return notification;
    }

    // 알림 목록 불러오기
    public List<Notification> findAll(int memberId) {
        return notificationRepository.findByMemberId(memberId);
    }

    public boolean readNotification(List<Long> notificationId) {
        try {
            if (notificationId.isEmpty()) return false;
            notificationRepository.readNoti(notificationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //    안 읽은 노티 세기
    public Integer countUnreadNotifications(int memberId) {
        return notificationRepository.countUnreadNotifications(memberId);
    }

    public boolean deleteNotification(Long notificationId) {
        try {
            notificationRepository.delete(notificationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
