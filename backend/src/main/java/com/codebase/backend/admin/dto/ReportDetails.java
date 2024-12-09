/*
김은지
2024 12 09
*/
package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDetails {

    private int detailId;

    private int reportId;
    private int memberId;

    private int category; // 프로젝트 0, 게시글 1, 게시글 댓글 2, 리뷰 3
    private int categoryId; // 각각 프로젝트, 게시글 id
    private String categoryTitle; // DB 저장 X, 신고 성공/실패 알림창에 사용

    private String content;
    private LocalDateTime reportDate;

}
