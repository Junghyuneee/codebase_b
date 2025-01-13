package com.codebase.backend.member.controller;

import com.codebase.backend.chat.service.ChatService;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<MemberDTO> myProfile(@AuthenticationPrincipal Member member) {
        return ResponseEntity.ok(MemberDTO.from(memberService.getMemberByEmail(member.getEmail())));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> profile(@AuthenticationPrincipal Member member, @RequestBody Map<String, Object> profile) {
        memberService.update(member, profile);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/password")
    public ResponseEntity<Boolean> password(@AuthenticationPrincipal Member member, @RequestBody Map<String, Object> password) {
        return ResponseEntity.ok(memberService.updatePassword(member, password));
    }

    //    회원 탈퇴
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMember(@AuthenticationPrincipal Member member, HttpServletRequest request, HttpServletResponse response) {

        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션을 무효화하여 로그아웃 처리
        }

        chatService.leaveAllChatroom(member);

        memberService.signout(response);
        return ResponseEntity.ok(memberService.removeMember(member));
    }
}
