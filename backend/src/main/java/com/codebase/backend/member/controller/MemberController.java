package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
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
    public ResponseEntity<MemberDTO> signup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        MemberDTO memberDTO = memberService.create(memberSignUpRequestBody);
        return ResponseEntity.ok(memberDTO);

    }

    @PostMapping("/oauth/signup")
    public ResponseEntity<MemberDTO> oauthSignup(@RequestBody MemberSignUpRequestBody memberSignUpRequestBody) {
        MemberDTO memberDTO = memberService.update(memberSignUpRequestBody);
        return ResponseEntity.ok(memberDTO);
    }
}
