package com.codebase.backend.project.dto;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class CartItem {
	private int id;
	
	//private String thumbnail; //아 ㅠ ㅅ ㅠ
	private String name;
	private int price;
	
	//외래키
	private int cart_id;

}
