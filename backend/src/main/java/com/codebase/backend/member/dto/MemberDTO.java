package com.codebase.backend.member.dto;

public record MemberDTO(int id, String name, String email, String addr, String postcode, String tel, int projectCount,
                        boolean role) {

    public static MemberDTO from(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddr(),
                member.getPostcode(),
                member.getTel(),
                member.getProjectCount(),
                member.isRole()
        );
    }
}
