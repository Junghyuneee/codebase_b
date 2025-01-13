package com.codebase.backend.project.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class Project{

	private int id;
	private String title;
	private String content;
	private long price;
	
	private int hit;
	private String link;
	private String img;
	private String types;
	private String username;
	// 외래키ㅐ
	private int maker_id;

	private char issoldout;//구매내역이 있을 경우 수정불가
	
	
	

}
