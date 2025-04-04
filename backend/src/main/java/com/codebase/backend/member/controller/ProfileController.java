package com.codebase.backend.member.controller;

import com.codebase.backend.chat.service.ChatService;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.service.MemberService;
import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.service.PostService;
import com.codebase.backend.projectteam.dto.ProjectteamDTO;
import com.codebase.backend.projectteam.service.ProjectteamService;
import com.codebase.backend.review.dto.Review;
import com.codebase.backend.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;
    private final ChatService chatService;
    private final PostService postService;
    private final ProjectteamService projectteamService;
    private final ReviewService reviewService;

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

    @GetMapping("/post/{name}")
    public ResponseEntity<List<PostDTO>> getMyPostList(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(postService.getPostByMemberName(name));
    }

    @GetMapping("/project/{name}")
    public ResponseEntity<List<ProjectteamDTO>> getMyProjectList(@PathVariable(value = "name") String name) {
        Member member = memberService.getMemberByName(name);
        return ResponseEntity.ok(projectteamService.getProjectTeamsByMemberId(member.getId()));
    }

    @GetMapping("/review/{name}")
    public ResponseEntity<List<Review>> getMyReviewList(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(reviewService.getReviewByAuthor(name));
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
