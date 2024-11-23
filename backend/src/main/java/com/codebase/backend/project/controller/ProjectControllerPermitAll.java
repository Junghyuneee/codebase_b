package com.codebase.backend.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codebase.backend.project.dto.Cart;
import com.codebase.backend.project.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/api/p/store")
public class ProjectControllerPermitAll {

	private final CartService cartService;
	
	@GetMapping("/api/store/create-cart")
	public void insertCart(String email) {

		cartService.insertCart("asdfaasdf");
		
	}
}
