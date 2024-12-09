/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Report {

    private int reportId;

    private int category; // 프로젝트 0, 게시글 1, 게시글 댓글 2, 리뷰 3
    private int categoryId; // 각각 프로젝트, 게시글 id
    private String categoryTitle;

    private int count; // 신고 수

    private boolean completed; // 신고 처리

    public Report(int category, int categoryId, String categoryTitle) {
        this.category = category;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

}
