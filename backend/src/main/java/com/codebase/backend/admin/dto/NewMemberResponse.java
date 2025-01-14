package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class NewMemberResponse {

    private int memberCount;
    private String joinDate;

    public NewMemberResponse(int memberCount, String joinDate) {
        this.memberCount = memberCount;
        this.joinDate = joinDate;
    }

}
