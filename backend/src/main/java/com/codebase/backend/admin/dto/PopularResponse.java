package com.codebase.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class PopularResponse {

    private int id; // 게시글 id
    private String title; // 게시글 제목
    private int hits; // 게시글 조회수

    public PopularResponse(int id, String title, int hits) {
        this.id = id;
        this.title = title;
        this.hits = hits;
    }

}
