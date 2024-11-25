package com.codebase.backend.review.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Review {
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private int views;

	/*
	 * public Integer getId(){ return id; }
	 * 
	 * public void setId(Integer id){ this.id = id; }
	 * 
	 * public String getTitle(){ return title; }
	 * 
	 * public void setTitle(String title){ this.title = title; }
	 * 
	 * public String getContent(){ return content; }
	 * 
	 * public void setContent(String content){ this.content = content; }
	 */
}