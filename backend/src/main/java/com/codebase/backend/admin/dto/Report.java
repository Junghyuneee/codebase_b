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
    private String content;
    private LocalDateTime date;

    private int category; // 프로젝트 0, 게시글 1
    private int categoryId; // 각각 프로젝트, 게시글 id
    private String categoryTitle;

    private int memberId;
    private String memberName;

    private boolean completed;

}
