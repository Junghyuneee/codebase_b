package com.codebase.backend.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.Payment;
import com.codebase.backend.project.dto.ProjectOrder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {
	
	@PostMapping("/api/store/payment/complete")
	public ResponseEntity<String> payment(@RequestBody Payment p, @AuthenticationPrincipal Member user) {
		//System.out.println(user);
		ProjectOrder bp = new ProjectOrder();
		bp.setBuyer_id(user.getId());
		p.setBuyer_id(user.getId());
		System.out.println("페이먼트 아이디 : "+ p.toString());
		return ResponseEntity.ok("");
	}

	
}
