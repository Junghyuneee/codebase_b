package com.codebase.backend.project.dto;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class Cart {
	//멤버생성시 생성
	
	private int id;

	//이메일 unique 확인
	private int member_email;

	
}
