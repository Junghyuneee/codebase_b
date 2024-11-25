package com.codebase.backend.member.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    private static final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername());
    }

    public String getUsername(String accessToken) {
        return getSubject(accessToken);
    }

    private String generateToken(String subject) {

        Date now = new Date();
        Date exp = new Date(now.getTime() + 1000 * 60 * 60 * 3);

        return Jwts.builder().subject(subject).signWith(key)
                .issuedAt(now)
                .expiration(exp)
                .compact();
    }

    private String getSubject(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();

        } catch (JwtException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
