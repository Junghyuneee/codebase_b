package com.codebase.backend.member.service;

import com.codebase.backend.member.response.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
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
        return generateToken(userDetails.getUsername(), 1000 * 60 * 30); // 30분
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), 1000L * 60 * 60 * 24 * 7); // 7일
    }

    public String getUsername(String accessToken) {
        return getSubject(accessToken);
    }

    private String generateToken(String subject, long validityDurationMs) {

        Date now = new Date();
        Date exp = new Date(now.getTime() + validityDurationMs);

        return Jwts.builder().subject(subject).signWith(key)
                .issuedAt(now)
                .expiration(exp)
                .compact();
    }

    private String getSubject(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        }catch (ExpiredJwtException e){
            log.error("Token expired: {}", e.getMessage());
            throw new TokenExpiredException("Token has expired.");
        }
        catch (JwtException e) {
            log.error(e.getMessage());
            throw e;
        }catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            log.error(e.getMessage());
            throw e;
        }
    }
}
