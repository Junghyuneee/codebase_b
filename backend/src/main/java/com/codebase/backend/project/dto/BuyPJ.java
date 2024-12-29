package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class BuyPJ {

	private int id;
	private String title;
	private String content;
	
	//private int hit;
	private String link;
	private String types;
	//private String maker_name;
	
	// 외래키ㅐ
	private int project_id;
	private int maker_id;
	private int buyer_id; // 필터에 사용하기
	private int payment_id;
	

}
