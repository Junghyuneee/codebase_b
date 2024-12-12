package com.codebase.backend.projectteam.DTO;

import lombok.Data;

@Data
public class TeamMemberDTO {
    private Integer tm_id;
    private Integer member_id;
    private Integer pjt_id;
    private String role;
    private String status;
}
