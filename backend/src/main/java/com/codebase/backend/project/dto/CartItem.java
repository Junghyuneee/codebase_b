package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class CartItem {
	private int id;
	
	//private String thumbnail; //아 ㅠ ㅅ ㅠ
	private String title;
	private long price;
	
	//외래키
	private int project_id;
	
	//외래키
	private int cart_id;

}
