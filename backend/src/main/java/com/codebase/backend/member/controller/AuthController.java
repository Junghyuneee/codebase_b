package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.MemberSigninRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<Void> isSignined(@AuthenticationPrincipal Member member) {
        return ResponseEntity.ok().build();
    }

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
    public ResponseEntity<Void> signout(HttpServletRequest request, HttpServletResponse response) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션을 무효화하여 로그아웃 처리
        }

        memberService.signout(response);
        return ResponseEntity.ok().build();
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
                return ResponseEntity.ok(Map.of("accessToken", accessToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
            }
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to refresh token.");
        }
    }
}