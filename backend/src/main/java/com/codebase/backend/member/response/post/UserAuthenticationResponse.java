package com.codebase.backend.member.response.post;

public record UserAuthenticationResponse(
        String accessToken,
        String email,
        String username,
        int member_id,
        int project_count
) {
}
