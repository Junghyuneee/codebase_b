package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.MemberSigninRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/search/{memberName}")
    public ResponseEntity<List<MemberDTO>> search(@PathVariable("memberName") String memberName) {
        return ResponseEntity.ok(memberService.searchMember(memberName));
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberDTO> profile(@AuthenticationPrincipal Member member, @RequestParam(value = "memberMail", required = false) String memberMail) {
        if (memberMail == null) {
            return ResponseEntity.ok(MemberDTO.from((Member) memberService.loadUserByUsername(member.getEmail()))); // 예: 기본값 반환
        } else {
            return ResponseEntity.ok(MemberDTO.from((Member) memberService.loadUserByUsername(memberMail)));
        }
    }
}
