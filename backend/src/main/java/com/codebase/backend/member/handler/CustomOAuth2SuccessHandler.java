package com.codebase.backend.member.handler;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.OAuth2Member;
import com.codebase.backend.member.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    @Value("${frontend.url}")
    private String frontendUrl;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2Member oAuth2Member = (OAuth2Member) authentication.getPrincipal();
        Member member = oAuth2Member.getMember();

        boolean needsSignup = member.getEmail() == null || member.getEmail().isEmpty() ||
                member.getName() == null || member.getName().isEmpty() ||
                member.getTel() == null || member.getTel().isEmpty() ||
                member.getAddr() == null || member.getAddr().isEmpty() ||
                member.getPostcode() == null || member.getPostcode().isEmpty();

        // 필요한 정보가 누락되었는지 확인

        if (needsSignup) {
            // 기존 데이터를 쿼리 파라미터로 전달하여 /signup으로 리디렉션
            String username = URLEncoder.encode(member.getEmail() != null ? member.getEmail() : "", StandardCharsets.UTF_8);
//            String name = URLEncoder.encode(customer.getName() != null ? customer.getName() : "", StandardCharsets.UTF_8);
//            String ctel = customer.getCtel() != null ? customer.getCtel() : "";

            String redirectUrl = String.format("%s/register?username=%s", frontendUrl, URLEncoder.encode(username, StandardCharsets.UTF_8));
            System.out.println("Redirecting to: " + redirectUrl);
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        } else {

            String refreshToken = jwtService.generateRefreshToken(member);
            String accessToken = jwtService.generateAccessToken(member);
            String redirectUrl = String.format(
                    "%s/oauth?token=%s&email=%s&name=%s&memberId=%s&project_count=%d",
                    frontendUrl,
                    URLEncoder.encode(accessToken, StandardCharsets.UTF_8),
                    URLEncoder.encode(member.getEmail(), StandardCharsets.UTF_8),
                    URLEncoder.encode(member.getName(), StandardCharsets.UTF_8),
                    member.getId(),
                    member.getProjectCount()
            );

            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setPath("/");
            refreshCookie.setHttpOnly(true);
            refreshCookie.setSecure(true);
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(7 * 24 * 60 * 60);

            response.addCookie(refreshCookie);

            response.sendRedirect(redirectUrl);
        }
    }
}
