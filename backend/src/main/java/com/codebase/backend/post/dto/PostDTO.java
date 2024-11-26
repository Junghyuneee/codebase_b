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
}
