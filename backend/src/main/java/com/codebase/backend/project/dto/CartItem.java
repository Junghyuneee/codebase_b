package com.codebase.backend.project.dto;

import lombok.Data;

@Data
public class CartItem {
	private int id;
	
	
	private String title;
	private long price;
	
	//외래키
	private int project_id;
	
	//외래키
	private int cart_id;
	
	//join
	private String img;

}
