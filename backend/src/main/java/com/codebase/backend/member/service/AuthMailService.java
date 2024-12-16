package com.codebase.backend.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

@RequiredArgsConstructor
@Service
public class AuthMailService {
    private final JavaMailSender mailSender;

    private final Map<String, Integer> authCodeStorage = new HashMap<>();
    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Value("${GOOGLE_MAIL_ID}")
    private String GOOGLE_MAIL_ID;

    // 인증번호 생성
    public Long createRandomNumber(String mail) {
        if (authCodeStorage.containsKey(mail)) {
            return getRemainingTime(mail);
        }

        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);

        authCodeStorage.put(mail, randomNumber);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(GOOGLE_MAIL_ID);
        mailMessage.setTo(mail);
        mailMessage.setSubject("Codebase 회원가입 인증번호 안내");
        mailMessage.setText(mail + "님의 인증번호는 " + randomNumber + "입니다.");
        mailSender.send(mailMessage);

        scheduleAuthCodeRemoval(mail);

        return getRemainingTime(mail);
    }

    // 인증번호 확인
    public boolean verifyAuthCode(String mail, Integer code) {
        System.out.println("mail = " + mail);
        System.out.println("code = " + code);
        if (authCodeStorage.containsKey(mail)) {
            Integer storedCode = authCodeStorage.get(mail);
            if (Objects.equals(storedCode, code)) {
                authCodeStorage.remove(mail);
                return true;
            }
        }
        return false;
    }

    // 인증번호 스케줄 설정
    private void scheduleAuthCodeRemoval(String mail) {
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            authCodeStorage.remove(mail);
            scheduledTasks.remove(mail);
            System.out.println("Auth code for " + mail + " has been removed after " + 3 + " minutes.");
        }, 3, TimeUnit.MINUTES);

        scheduledTasks.put(mail, future);
    }

    // 남은 시간 확인
    private long getRemainingTime(String mail) {
        ScheduledFuture<?> future = scheduledTasks.get(mail);
        if (future != null) {
            return future.getDelay(TimeUnit.SECONDS); // 남은 시간을 초 단위로 반환
        }
        return -1; // 없으면 -1 반환
    }
}
