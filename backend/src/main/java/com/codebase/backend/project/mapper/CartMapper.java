package com.codebase.backend.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.Cart;

@Mapper
public interface CartMapper {
	int insertCart(Cart cart);
}
