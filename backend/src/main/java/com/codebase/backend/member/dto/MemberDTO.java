package com.codebase.backend.member.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    // private Project  // private Cart 
}
