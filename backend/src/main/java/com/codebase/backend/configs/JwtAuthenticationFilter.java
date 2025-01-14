package com.codebase.backend.configs;

import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.data.util.Pair;
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
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    // 예외 경로 목록을 (HTTP 메서드, URL)로 관리
    private final Set<Pair<String, String>> excludedPaths = new HashSet<>();
    private static final String ANY_METHOD = "ANY";

    // 생성자에서 예외 경로를 미리 등록
    public JwtAuthenticationFilter(JwtService jwtService, MemberRepository memberRepository) {
        this.jwtService = jwtService;
        this.memberRepository = memberRepository;

        /*admin dashboard*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/dashboard"));

        /*member 검색, 프로필*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/member"));
        /*회원가입, 로그인, 로그아웃*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/auth"));

        /*소켓 연결 나중에 interceptor 처리해야*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/stomp"));

        /*리뷰*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/review"));

        /*게시글*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/post"));
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/comments"));

        /*스토어*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/store"));

        /*팀*/
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/projectteams"));
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/team-applications"));
        excludedPaths.add(Pair.of(ANY_METHOD, "/api/teammembers"));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String BEARER_PREFIX = "Bearer ";
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();
        System.out.println("requestPath = " + requestPath);
        System.out.println("requestMethod = " + requestMethod);
        System.out.println("shouldSkipAuthentication(requestMethod, requestPath) = " + shouldSkipAuthentication(requestMethod, requestPath));

        // 예외 경로에 포함되면 인증 스킵
        if (shouldSkipAuthentication(requestMethod, requestPath)) {
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

    // 메서드와 경로를 함께 체크하여 예외 처리
    private boolean shouldSkipAuthentication(String method, String requestPath) {
        return excludedPaths.stream()
                .anyMatch(pair ->
                        requestPath.startsWith(pair.getSecond()) &&
                                (pair.getFirst().equalsIgnoreCase(method) || pair.getFirst().equalsIgnoreCase(ANY_METHOD))
                );
    }
}
