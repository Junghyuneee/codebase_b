package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class ProjectOrder {

	private int id;

	// 외래키ㅐ
	private int project_id;
	private int maker_id;
	private int buyer_id; // 필터에 사용하기
	private int payment_id;
	
	private String title;
	private String content;
	private String link;
	private String types;
	private String username;
	private String img;
	
}
