package com.codebase.backend.admin.controller;

import com.codebase.backend.admin.repository.MemberAndChatRepository;
import com.codebase.backend.admin.service.MemberAndChatService;
import com.codebase.backend.member.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class MemberAndChatController {

    @Autowired
    private MemberAndChatService memberAndChatService;

    @CrossOrigin(origins = "http://localhost:5713")
    @GetMapping("/member")
    public ResponseEntity<Map<String, Object>> getAllReport(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = memberAndChatService.getAllMembers(page, size);
        return ResponseEntity.ok(response);
    }

}
