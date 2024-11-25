package com.codebase.backend.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.project.dto.CartItem;
import com.codebase.backend.project.mapper.CartItemMapper;
import com.codebase.backend.project.mapper.ProjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {
	
	private final CartItemMapper cartItemMapper;
	
	
	void insertCartItem(CartItem cartItem) {
		cartItemMapper.insertCartItem(cartItem);
	}
    List<CartItem> selectCartItemByCartId(int id){
    	return cartItemMapper.selectCartItemByCartId(id);
    }
    CartItem findById(int id) {
    	return cartItemMapper.findById(id);
    }
    List<CartItem> findByCartId(int id){
    	return cartItemMapper.findByCartId(id);
    }
    void deleteCartItemById(int id) {
    	cartItemMapper.deleteCartItemById(id);
    }

	
}
