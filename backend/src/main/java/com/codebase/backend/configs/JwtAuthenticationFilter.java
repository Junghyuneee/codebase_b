package com.codebase.backend.configs;

import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    // 예외 경로 목록을 Set으로 관리 (prefix만 관리)
    private final Set<String> excludedPaths = new HashSet<>();

    // 생성자에서 예외 경로를 미리 등록
    public JwtAuthenticationFilter(JwtService jwtService, MemberRepository memberRepository) {
        this.jwtService = jwtService;
        this.memberRepository = memberRepository;
        excludedPaths.add("/dashboard");
        excludedPaths.add("/member/search");
        excludedPaths.add("/member/name");
        excludedPaths.add("/member/mail");
        excludedPaths.add("/auth/refresh");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String BEARER_PREFIX = "Bearer ";
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String requestPath = request.getServletPath();

        // 예외 경로에 포함되면 인증 스킵
        if (shouldSkipAuthentication(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        // JWT 인증 처리
        if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith(BEARER_PREFIX) && securityContext.getAuthentication() == null) {
            String accessToken = authorization.substring(BEARER_PREFIX.length());
            try {
                String username = jwtService.getUsername(accessToken);
                UserDetails userDetails = memberRepository.findByEmail(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
                response.flushBuffer(); // 스트림 종료
                return;
            } catch (Exception e) {
                // 예상치 못한 오류 로그 추가
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("An unexpected error occurred");
                response.flushBuffer(); // 스트림 종료
                return;
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT token");
            response.flushBuffer(); // 스트림 종료
            return;
        }

        filterChain.doFilter(request, response);
    }

    // 경로가 예외 목록에 포함되는지 체크
    private boolean shouldSkipAuthentication(String requestPath) {
        // 예외 경로 목록에 해당 경로가 포함되면 true 반환
        return excludedPaths.stream().anyMatch(requestPath::startsWith);
    }
}
