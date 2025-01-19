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
public class ReportDetail {

    private int detailId;

    private int reportId;
    private Integer memberId;

    private String content;
    private LocalDateTime reportDate;


}
