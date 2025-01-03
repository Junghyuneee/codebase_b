package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDetailResponse {

    private int detailId;

    private int reportId;
    private String memberEmail;

    private String content;
    private String reportDate;

}
