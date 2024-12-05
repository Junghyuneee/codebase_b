package com.codebase.backend.post.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PostDTO {
    private Long id; // 게시글 ID
    private String topic; // 게시글 주제
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String author; // 작성자
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 날짜 형식 지정
    private LocalDateTime createDate; // 등록일
    private int views; // 조회수
    private int likeCount; // 좋아요 수
    private int dislikeCount; // 싫어요 수
}
