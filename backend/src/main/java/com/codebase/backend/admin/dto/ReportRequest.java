package com.codebase.backend.admin.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {

    @NotNull
    private int category;

    @NotNull
    private int categoryId;

    private int memberId;

    @NotBlank(message = "신고 사유 선택은 필수 입니다.")
    private String content;

}
