package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.service.MemberAndChatService;
import com.codebase.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
@RequiredArgsConstructor
public class MemberAndChatController {

    private final MemberAndChatService memberAndChatService;

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/member")
    public ResponseEntity<Map<String, Object>> getAllReport(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = memberAndChatService.getAllMembers(page, size);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5713")
    @PostMapping("/changeMemberRole/{memberId}")
    public ResponseEntity<Map<String, Object>> changeRole(@PathVariable int memberId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 권한 변경
            String memberName = memberAndChatService.changeRole(memberId);

            // 성공 응답 (JSON 형식)
            response.put("memberName", memberName);
            response.put("status", "success");
            response.put("message", "멤버 권한이 변경되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 응답 (JSON 형식)
            response.put("memberName", "");
            response.put("status", "success");
            response.put("message", "멤버 권한에 실패했습니다.");
            return ResponseEntity.ok(response);
        }
    }

}
