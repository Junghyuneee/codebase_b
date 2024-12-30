package com.codebase.backend.member.controller;

import com.codebase.backend.chat.service.ChatService;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.MemberSigninRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.member.service.AuthMailService;
import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.member.service.MemberService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final AuthMailService authMailService;
    private final ChatService chatService;

    /* 이메일 회원가입 시 닉네임 중복검사 */
    @GetMapping("/namecheck")
    public ResponseEntity<Boolean> nameCheck(@RequestParam("name") String name) {
        Member member = memberService.getMemberByName(name);
        return ResponseEntity.ok(member == null);
    }

    /* 이메일 회원가입 시 이메일 중복검사 */
    @GetMapping("/emailcheck")
    public ResponseEntity<Long> emailCheck(@RequestParam("email") String email) {
        Member member = memberService.getMemberByEmail(email);
        if (member == null) {
            return ResponseEntity.ok(authMailService.createRandomNumber(email));
        } else {
            return ResponseEntity.ok(-1L);
        }
    }

    /* 이메일 인증 코드 */
    @GetMapping("/emailverify")
    public ResponseEntity<Boolean> emailVerify(@RequestParam("email") String email, @RequestParam("code") Integer code) {
        return ResponseEntity.ok(authMailService.verifyAuthCode(email, code));
    }

    /* 이메일 회원 가입 */
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        Member member = memberService.create(memberSignUpRequestBody);
        return ResponseEntity.ok(member);
    }

    /* 소셜 회원가입 */
    @PostMapping("/oauth/signup")
    public ResponseEntity<Member> oauthSignup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        Member member = memberService.socialCreate(memberSignUpRequestBody);
        return ResponseEntity.ok(member);
    }

    /* 로그인 */
    @PostMapping("/signin")
    public ResponseEntity<UserAuthenticationResponse> signin(@RequestBody MemberSigninRequestBody memberSigninRequestBody, HttpServletResponse response) {
        return ResponseEntity.ok(memberService.login(memberSigninRequestBody.email(), memberSigninRequestBody.password(), response));
    }

    /* 로그아웃 */
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

    /* 액세스 토큰 재발급 */
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        try {
            if (refreshToken == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No refresh token found.");
            }

            String username = jwtService.getUsername(refreshToken);
            String accessToken = memberService.refreshToken(username, response);
            Member member = memberService.getMemberByEmail(username);
            return ResponseEntity.ok(new UserAuthenticationResponse(
                    accessToken,
                    member.getEmail(), member.getName(), member.getId(), member.isRole()));

        } catch (JwtException e) {
            memberService.signout(response);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token.");
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to refresh token.");
        }
    }
}