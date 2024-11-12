package com.codebase.backend.admin.dto;

import java.time.LocalDateTime;

public class Complaint {

    private int complaintId;
    private String content;
    private LocalDateTime date;
    private int category;

    private int memberId;
    private String memberName;
    private boolean completed;

}
