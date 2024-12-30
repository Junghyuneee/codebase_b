package com.codebase.backend.comment.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id; // 댓글 ID
    private Long postId; // 게시물 ID
    private String content; // 댓글 내용
    private String author; // 작성자
    private LocalDateTime createdAt; // 생성일시

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // 생성자
    public CommentDto() {
        this.createdAt = LocalDateTime.now(); // 현재 시간으로 초기화
    }
}
