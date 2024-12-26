package com.codebase.backend.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.project.dto.CartItem;
import com.codebase.backend.project.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
	
	private final CartItemService cartItemService;
	
	
	@GetMapping("/my")
	public List<CartItem> listCI (@AuthenticationPrincipal Member user){
		System.out.println("확인용 " + cartItemService.findByCartId(user.getCart_id()));
		return cartItemService.findByCartId(user.getCart_id());
	}
	
	
//	@GetMapping("/my/{page}")
//	public List<CartItem> listCIPage (@PathVariable("page") Integer page, @AuthenticationPrincipal Member user){
//		System.out.println(cartItemService.findByCartIdPage(user.getCart_id(),page));
//		return cartItemService.findByCartIdPage(user.getCart_id(),page);
//	}
	
	
	
	@PostMapping("/add")
	public void addCartItem(CartItem cartitem, @AuthenticationPrincipal Member user) {
		System.out.println(user.toString());
		cartitem.setCart_id(user.getCart_id());
		System.out.println(cartitem.toString());
		cartItemService.insertCartItem(cartitem);
		return;
	} 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delCartItem(@PathVariable("id") int project_id, @AuthenticationPrincipal Member user) {
		CartItem ci = cartItemService.findByProjectCartId(user.getCart_id(), project_id);
		if(user.getCart_id() != ci.getCart_id()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한없음");
		}
		
		System.out.println(project_id);
		cartItemService.deleteByProjectCartId(user.getCart_id(), project_id);
		return ResponseEntity.ok(ci.getTitle() + " 삭제");
	} 
	

	@DeleteMapping("/deleteitem/{id}")
	public ResponseEntity<String> delItemInCart(@PathVariable("id") int cartitem_id, @AuthenticationPrincipal Member user) {
		System.out.println("카트에서 아이템" + cartitem_id);
		
		CartItem ci = cartItemService.findById(cartitem_id);
		if(user.getCart_id() != ci.getCart_id()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("권한없음");
		}
		cartItemService.deleteCartItemById(cartitem_id);
		return ResponseEntity.ok(ci.getTitle() + " 삭제");
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
