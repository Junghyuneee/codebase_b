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

    public Report() {} // MyBatis는 기본 생성자가 없으면 매핑할 때 객체 생성에 어려움을 겪을 수 있습니다.

}
