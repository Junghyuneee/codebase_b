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
	
	
	public void insertCartItem(CartItem cartItem) {	
		cartItemMapper.insertCartItem(cartItem);
	}
	public List<CartItem> selectCartItemByCartId(int id){
    	return cartItemMapper.selectCartItemByCartId(id);
    }
	public CartItem findById(int id) {
    	return cartItemMapper.findById(id);
    }
	public List<CartItem> findByCartId(int id){
    	return cartItemMapper.findByCartId(id);
    }
	public CartItem findByProjectCartId(int cart_id, int project_id){
		return cartItemMapper.findByProjectCartId(cart_id, project_id);
	}
	public void deleteCartItemById(int id) {
    	cartItemMapper.deleteCartItemById(id);
    }
	public void deleteByProjectCartId(int cart_id, int project_id) {
		cartItemMapper.deleteByProjectCartId(cart_id, project_id);
	}

	
}
