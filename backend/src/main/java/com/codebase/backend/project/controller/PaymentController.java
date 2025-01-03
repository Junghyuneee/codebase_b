package com.codebase.backend.project.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.Payment;
import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/payment")
public class PaymentController {
	
	private final PaymentService paymentService;

	@PostMapping("/complete")
	public ResponseEntity<String> payment(@RequestBody Payment p, @AuthenticationPrincipal Member user) {
		//System.out.println(user);
		ProjectOrder po = new ProjectOrder();
		po.setBuyer_id(user.getId());
		po.setProject_id(p.getProject_id());
		p.setBuyer_id(user.getId());
		
		paymentService.save(p);

		System.out.println("페이먼트 아이디 : "+ p.toString());
		
		return ResponseEntity.ok("ooooooooooook");
	}
	
}
