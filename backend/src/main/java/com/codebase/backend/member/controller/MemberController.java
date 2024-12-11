package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/search/{memberName}")
    public ResponseEntity<List<MemberDTO>> search(@PathVariable("memberName") String memberName) {
        return ResponseEntity.ok(memberService.searchMember(memberName).stream().map(MemberDTO::from).collect(Collectors.toList()));
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
