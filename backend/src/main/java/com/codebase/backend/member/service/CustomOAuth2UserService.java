package com.codebase.backend.member.service;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.OAuth2Member;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.project.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    final MemberRepository memberRepository;
    private final CartService cartService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String email = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("email");
            case "kakao" -> {
                Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
                yield kakaoAccount.get("email").toString();
            }
            default -> "";
        };

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            member = registerMember(userRequest, oAuth2User);
        }

        return new OAuth2Member(member, oAuth2User.getAttributes());
    }

    public Member registerMember(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        Member member = MemberFactory.create(userRequest, oAuth2User);
        int cart_id = cartService.insertCart(member.getEmail());
        member.setCart_id(cart_id);
        return memberRepository.save(member);
    }
}
