package com.codebase.backend.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MemberDTO {
    private int id;

    private String name;

    private String email;

    private String password;

    // true: admin, false user
    private boolean role;

    private String addr;

    private String postcode;

    private String tel;

    private LocalDate createdAt;

    private int projectCount;

    // Foreign Key
    // private Project
}
