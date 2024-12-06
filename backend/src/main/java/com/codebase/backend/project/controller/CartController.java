package com.codebase.backend.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.CartItem;
import com.codebase.backend.project.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final CartItemService cartItemService;
	
	
	
	@PostMapping("/add")
	public void addCartItem(CartItem cartitem, @AuthenticationPrincipal Member user) {
		System.out.println(user.toString());
		cartitem.setCart_id(user.getCart_id());
		System.out.println(cartitem.toString());
		cartItemService.insertCartItem(cartitem);
		return;
	} 
	
	@GetMapping("/ciexist/{id}")
	public CartItem checkci(@AuthenticationPrincipal Member user, @PathVariable("id") Integer project_id) {
		System.out.println(project_id);
		System.out.println(user.getCart_id());
		//System.out.println(cartItemService.findByProjectCartId(999, 17));
		return cartItemService.findByProjectCartId(user.getCart_id(), project_id);
		//return project_id + " " + user.getCart_id();
	}

}
