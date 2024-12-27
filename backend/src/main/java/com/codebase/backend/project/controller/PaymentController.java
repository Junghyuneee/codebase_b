package com.codebase.backend.project.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {
	

	
	@GetMapping("/api/store/payment/complete")
	@ResponseBody
	public String payment() {
		
	
		return "asdfasdf";
	}
	
}
