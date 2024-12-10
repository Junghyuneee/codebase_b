package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.MemberSigninRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    @Value("${frontend.url}")
    private String frontendUrl;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        Member member = memberService.create(memberSignUpRequestBody);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/oauth/signup")
    public ResponseEntity<Member> oauthSignup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        Member member = memberService.update(memberSignUpRequestBody);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserAuthenticationResponse> signin(@RequestBody MemberSigninRequestBody memberSigninRequestBody, HttpServletResponse response) {
        return ResponseEntity.ok(memberService.login(memberSigninRequestBody.email(), memberSigninRequestBody.password(), response));
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signout(HttpServletResponse response) {
        System.out.println("signout");
        memberService.signout(response);

        // 리다이렉트할 URL
        String redirectUrl = frontendUrl + "/";  // 메인 페이지로 리다이렉트

        return ResponseEntity.status(HttpStatus.FOUND)  // 302 Found 상태 코드
                .header(HttpHeaders.LOCATION, redirectUrl)  // 리다이렉트 URL
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken") String refreshToken, HttpServletResponse response) {
        try {
            if (refreshToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String username = jwtService.getUsername(refreshToken);

            if (username != null) {
                String accessToken = memberService.refreshToken(username, response);
                return ResponseEntity.ok(Map.of("access_token", accessToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to refresh token.");
        }
    }
}