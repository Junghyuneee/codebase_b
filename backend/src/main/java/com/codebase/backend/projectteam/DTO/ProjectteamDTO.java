package com.codebase.backend.projectteam.DTO;

import lombok.Data;

@Data
public class ProjectteamDTO {
	private Integer pjt_id;           // 프로젝트 ID
    private String pjtname;       // 프로젝트 이름
    private String pjtowner;      // 프로젝트 소유자
    private String pjtimg;        // 프로젝트 이미지
    private String pjtdescription; // 프로젝트 설명
    private String pjcategory;    // 프로젝트 카테고리
    private Integer member_id;   //외래 키
 // Getters and Setters
    public Integer getPjtId() {
        return pjt_id;
    }

    public void setPjtId(Integer pjt_id) {
        this.pjt_id = pjt_id;
    }

    public String getPjtname() {
        return pjtname;
    }

    public void setPjtname(String pjtname) {
        this.pjtname = pjtname;
    }

    public String getPjtowner() {
        return pjtowner;
    }

    public void setPjtowner(String pjtowner) {
        this.pjtowner = pjtowner;
    }

    public String getPjtimg() {
        return pjtimg;
    }

    public void setPjtimg(String pjtimg) {
        this.pjtimg = pjtimg;
    }

    public String getPjtdescription() {
        return pjtdescription;
    }

    public void setPjtdescription(String pjtdescription) {
        this.pjtdescription = pjtdescription;
    }

    public String getPjcategory() {
        return pjcategory;
    }

    public void setPjcategory(String pjcategory) {
        this.pjcategory = pjcategory;
    }

    public Integer getMemberId() {
        return member_id;
    }

    public void setMemberId(Integer member_id) {
        this.member_id = member_id;
    }
    
}