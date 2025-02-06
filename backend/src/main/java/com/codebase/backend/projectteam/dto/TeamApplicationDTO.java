package com.codebase.backend.projectteam.dto;

import lombok.Data;

@Data
public class TeamApplicationDTO {
    private Integer application_id;
    private Integer member_id;
    private Integer pjt_id;
    private TechStack tech_stack;
    private Status status;

    public Integer getApplication_id() {
        return application_id;
    }

    public void setApplication_id(Integer application_id) {
        this.application_id = application_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getPjt_id() {
        return pjt_id;
    }

    public void setPjt_id(Integer pjt_id) {
        this.pjt_id = pjt_id;
    }
        
    public TechStack getTech_stack() {
        return tech_stack;
    }
    
    public void setTech_stack(TechStack tech_stack) {
        this.tech_stack = tech_stack;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
 }

