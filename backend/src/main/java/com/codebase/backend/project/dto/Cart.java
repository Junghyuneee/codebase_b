package com.codebase.backend.project.dto;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class Cart {
	//멤버생성시 생성
	
	private int id;// 편의상

	//외래키 아님. 닉네임 또는 이메일 중 unique키가 생성되면,
	private int member_email;
	
	//id를 외래키로 쓰려면 생성 후 재호출 필요.
	
}
