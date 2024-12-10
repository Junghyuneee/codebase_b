package com.codebase.backend.member.response.post;

public record UserAuthenticationResponse(
        String accessToken,
        String email,
        String username,
        Integer member_id,
        Integer project_count
) {
}
