package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class Cart {
	//멤버생성시 생성
	
	private int id;

	//이메일 unique 확인
	private String member_email;

	
}
