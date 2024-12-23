package com.codebase.backend.review.dto;

import java.time.LocalDateTime;

import com.codebase.backend.projectteam.dto.ProjectteamDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//@Getter
//@Setter
//@Data
public class Review {
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private int views;
    private int likes;
    private int dislikes;
    @JsonProperty("pjt_id") // JSON의 pjt_id와 매핑
    private int pjtId;
    
    //private ProjectteamDTO projectteamDTO;
    
    // Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public int getPjtId() {
		return pjtId;
	}
	public void setPjtId(int pjtId) {
		this.pjtId = pjtId;
	}
	


}