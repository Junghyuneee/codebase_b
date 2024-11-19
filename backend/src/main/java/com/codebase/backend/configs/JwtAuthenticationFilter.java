package com.codebase.backend.configs;

import com.codebase.backend.member.service.JwtService;
import com.codebase.backend.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO: JWT 검증

        String BEARER_PREFIX = "Bearer ";
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith(BEARER_PREFIX) && securityContext.getAuthentication() == null) {
            String accessToken = authorization.substring(BEARER_PREFIX.length());

            String username = jwtService.getUsername(accessToken);
            UserDetails userDetails = memberService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);
        }

        filterChain.doFilter(request, response);
    }
}
