package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class BuyPJ {

	private int id;
	private String name;//수정시 동기화 문제
	private String content;
	private int price;
	
	//private int hit;
	private String link;
	private String types;
	private String maker_name; //편의상 넣기

	// 외래키ㅐ
	private int project_id;
	private int maker_id;
	private int buyer_id; // 필터에 사용하기
	

}
