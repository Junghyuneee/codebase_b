package com.codebase.backend.project;

import com.codebase.backend.member.dto.MemberDTO;

import lombok.Data;

@Data
public class Project {

	private int project_id;
	private String name;
	private String content;
	private int price;
	
	private int hit;
	private String link;
	private String types;

	private String username;
	// 외래키ㅐ
	private int maker_id;
	
	
	

}
