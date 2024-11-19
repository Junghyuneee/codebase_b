package com.codebase.backend.project.dto;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class Cart {
	private int id;
	private String username; //또는 MemberDTO member
	
}
