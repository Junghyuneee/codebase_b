package com.codebase.backend.review.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReviewComment {
	
	private int id;
	private int reviewId;
	private String content;
	private Timestamp createdDate;

}
