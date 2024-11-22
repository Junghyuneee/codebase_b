package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.CartItem;

@Mapper
public interface CartItemMapper {
    void insertCartItem(CartItem cartItem);
    List<CartItem> selectCartItemByCartId(int id);
    CartItem findById(int id);
    List<CartItem> findByCartId(int id);
    void deleteCartItemById(int id);
}