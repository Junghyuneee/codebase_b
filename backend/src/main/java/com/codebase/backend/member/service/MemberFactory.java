package com.codebase.backend.member.service;

import com.codebase.backend.member.dto.Member;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.util.Map;

public class MemberFactory {
    public static Member create(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        return switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "kakao" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttribute("kakao_account");
                yield Member.builder()
                        .email(attributeMap.get("email").toString())
                        .role(false)
                        .createdAt(LocalDate.now())
                        .projectCount(3)
                        .build();
            }
            case "google" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttributes();
                yield Member.builder()
                        .email(attributeMap.get("email").toString())
                        .name(attributeMap.get("name").toString())
                        .role(false)
                        .build();
            }
            default -> throw new IllegalArgumentException("연동되지 않은 서비스입니다.");
        };
    }
}
