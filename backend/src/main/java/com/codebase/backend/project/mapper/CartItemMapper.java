package com.codebase.backend.project.mapper;

import java.util.List;

import com.codebase.backend.project.dto.CartItem;

public interface CartItemMapper {
    void insertCartItem(CartItem cartItem);
    List<CartItem> selectCartItemByCartId(int id);
    CartItem findById(int id);
    void deleteCartItemById(int id);
}