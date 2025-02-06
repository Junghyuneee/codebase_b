package com.codebase.backend.project.controller;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.Payment;
import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.mapper.ProjectOrderMapper;
import com.codebase.backend.project.service.PaymentService;
import com.codebase.backend.project.service.ProjectOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/u/store/payment")
public class PaymentController {
	
	private final PaymentService paymentService;
	private final ProjectOrderService projectOrderService;
	private final ProjectOrderMapper projectOrderMapper;
	
	
	
	@PostMapping("/complete")
	public ResponseEntity<String> payment(@RequestBody Payment p, @AuthenticationPrincipal Member user) {
		//System.out.println(user);
		boolean exist = projectOrderService.existCheck(user.getId(), p.getProject_id());
		
		System.out.println(exist);
		
		if(exist) {
			System.out.println("이미 구매 됨 : "+ user.getId() + " " + p.getProject_id());
			return ResponseEntity.ok("이미 구매한 상품입니다. 중복 처리 시 문의");
			
		}
		
		
		ProjectOrder po = new ProjectOrder();
		po.setBuyer_id(user.getId());
		po.setProject_id(p.getProject_id());
		p.setBuyer_id(user.getId());
		
		paymentService.save(p);

		System.out.println("~Payment : "+ p.toString());
		
		return ResponseEntity.ok("ooooooooooook");
	}
	
	
	@GetMapping("/exist/{project_id}")
	public boolean existPayment(@AuthenticationPrincipal Member user, @PathVariable("project_id") int project_id) {
		boolean exist = projectOrderService.existCheck(user.getId(), project_id);
		return exist;
	}
	
	
}
