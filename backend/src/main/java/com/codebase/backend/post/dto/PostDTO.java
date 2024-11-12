package com.codebase.backend.post.dto;

import java.lang.reflect.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long pid;
    private String pname;
    private String pcategory;
    private String pcontent;
    private Member maker;
    private int phit;
    private Long memberId; // 게시글 작성자의 ID
}
