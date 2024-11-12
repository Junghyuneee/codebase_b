package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Visitor {

    private int visitId;
    private String visitIp;
    private LocalDateTime visitDate;

    public Visitor(String visitIp, LocalDateTime visitDate) {
        this.visitIp = visitIp;
        this.visitDate = visitDate;
    }

}
