package com.codebase.backend.post.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * PostDTO represents the Data Transfer Object for posts.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PostDTO {
    private int id; // 게시글 ID
    private String topic; // 게시글 주제
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String author; // 작성자
    private List<String> tags; // 태그 리스트 (optional)
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // 날짜 형식 지정
    private LocalDateTime createDate; // 등록일
    
    private int views; // 조회수
    private int likes; // 좋아요 수
    private int dislikes; // 싫어요 수

    // Optional: Custom constructor for required fields
    public PostDTO(String topic, String title, String content, String author) {
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = LocalDateTime.now(); // 현재 시간으로 초기화
        this.views = 0; // 초기 조회수
        this.likes = 0; // 초기 좋아요 수
        this.dislikes = 0; // 초기 싫어요 수
    }
}