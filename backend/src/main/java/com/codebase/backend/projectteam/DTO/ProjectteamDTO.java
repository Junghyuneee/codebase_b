package com.codebase.backend.projectteam.DTO;

public class ProjectteamDTO {
	private Integer pjtId;           // 프로젝트 ID
    private String pjtname;       // 프로젝트 이름
    private String pjtowner;      // 프로젝트 소유자
    private String pjtimg;        // 프로젝트 이미지
    private String pjtdescription; // 프로젝트 설명
    private String pjcategory;    // 프로젝트 카테고리

    // Getters and Setters
    public Integer getPjtId() {
        return pjtId;
    }

    public void setPjtId(Integer pjtId) {
        this.pjtId = pjtId;
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
}