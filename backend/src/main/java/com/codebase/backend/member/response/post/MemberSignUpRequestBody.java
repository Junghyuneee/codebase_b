package com.codebase.backend.member.response.post;

public record MemberSignUpRequestBody (
    String email,
    String password,
    String name,
    String addr,
    String postcode,
    String tel
){}