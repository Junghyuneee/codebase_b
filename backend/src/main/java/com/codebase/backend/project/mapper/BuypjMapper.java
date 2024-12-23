package com.codebase.backend.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.project.dto.BuyPJ;

@Mapper
public interface BuypjMapper {

	void save(BuyPJ buyPJ);
	List<BuyPJ>findByBuyer(int buyer_id);
	void deleteById(int id);
	BuyPJ get();
}
