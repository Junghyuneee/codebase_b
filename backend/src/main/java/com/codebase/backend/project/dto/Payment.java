package com.codebase.backend.project.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Payment {

	private int id;
	private String paymentId;
	
	private int buyer_id;
	private int project_id;
	private int price;
	private LocalDateTime purchaseTime;

	
}
