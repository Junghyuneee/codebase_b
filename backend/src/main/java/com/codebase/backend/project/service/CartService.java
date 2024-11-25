package com.codebase.backend.project.service;

import org.springframework.stereotype.Service;

import com.codebase.backend.project.dto.Cart;
import com.codebase.backend.project.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartMapper cartMapper;
	
	public int insertCart(String email) {
		Cart cart = new Cart();
        cart.setMember_email(email);
        
        // Cart 객체를 데이터베이스에 삽입하고 생성된 ID를 변수에 저장
        cartMapper.insertCart(cart);
        System.out.println("s, 카트아이디 : " + cart.getId());
        // 생성된 Cart ID를 반환
        return cart.getId();

	}
	
}
