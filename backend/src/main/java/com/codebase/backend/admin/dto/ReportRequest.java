package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {

    private int category;
    private int categoryId;
    private String categoryTitle;

    private int memberId;

    private String content;

}
