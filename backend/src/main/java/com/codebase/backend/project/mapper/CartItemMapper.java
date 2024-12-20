package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codebase.backend.project.dto.CartItem;

@Mapper
public interface CartItemMapper {
    void insertCartItem(CartItem cartItem);
    List<CartItem> selectCartItemByCartId(int id);
    CartItem findById(int id);
    List<CartItem> findByCartId(int id);
    List<CartItem> findByCartIdPage(@Param("cart_id") int cart_id, @Param("offset") int offset);
    CartItem findByProjectCartId(@Param("cart_id") int cart_id, @Param("project_id") int project_id);
    void deleteCartItemById(int id);
    void deleteByProjectCartId(@Param("cart_id") int cart_id, @Param("project_id") int project_id);
    void deleteByProjectId(int project_id);
}