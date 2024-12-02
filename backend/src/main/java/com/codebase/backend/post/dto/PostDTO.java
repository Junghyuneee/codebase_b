package com.codebase.backend.post.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String topic;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private int views;
    private int likeCount; // 좋아요 수
    private int dislikeCount; // 싫어요 수
}
