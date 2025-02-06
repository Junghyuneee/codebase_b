package com.codebase.backend.member.controller;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    /* 다른 사용자 프로필 */
    @GetMapping("/name/{memberName}")
    public ResponseEntity<MemberDTO> findByMemberName(@PathVariable(value = "memberName") String memberName) {
        Member member = memberService.getMemberByName(memberName);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberDTO.from(member));
    }

    @GetMapping("/mail/{memberMail}")
    public ResponseEntity<MemberDTO> findByMemberMail(@PathVariable(value = "memberMail") String memberMail) {
        Member member = memberService.getMemberByEmail(memberMail);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberDTO.from(member));
    }
}
