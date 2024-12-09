/*
김은지
2024 11 25
*/
package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {

    private int reportId;

    private int category; // 프로젝트 0, 게시글 1
    private int categoryId; // 각각 프로젝트, 게시글 id
    private String categoryTitle; // 제목

    private int count;

    private boolean completed;

    public ReportDTO(int reportId, int category, int categoryId, String categoryTitle,
                     int count, boolean completed) {
        this.reportId = reportId;
        this.category = category;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.count = count;
        this.completed = completed;
    }

}
