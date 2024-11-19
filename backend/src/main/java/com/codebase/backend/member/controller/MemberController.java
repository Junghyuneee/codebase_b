package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.MemberSigninRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

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

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody MemberSigninRequestBody memberSigninRequestBody) {
        UserAuthenticationResponse response = memberService.login(memberSigninRequestBody.email(), memberSigninRequestBody.password());

        return ResponseEntity.ok(response);

    }
}
