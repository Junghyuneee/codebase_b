package com.codebase.backend.project.dto;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class BuyPJ {

	private int id;
	private String name;
	private String content;
	private int price;
	
	private int hit;
	private String link;
	private String types;

	//private String username;
	// 외래키ㅐ
	private MemberDTO maker;
	private MemberDTO buyer; // 필터에 사용하기
	

}
