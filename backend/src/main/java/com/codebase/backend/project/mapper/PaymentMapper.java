package com.codebase.backend.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.Payment;

@Mapper
public interface PaymentMapper {
	void save(Payment p);

}
