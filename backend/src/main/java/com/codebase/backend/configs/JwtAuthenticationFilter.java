package com.codebase.backend.configs;

import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
// import org.springframework.data.util.Pair; // 이 라인을 제거했습니다.
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
import java.util.AbstractMap.SimpleEntry; // SimpleEntry를 사용하여 (키, 값) 쌍을 표현합니다.
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    // 예외 경로 목록을 (HTTP 메서드, URL)로 관리합니다.
    private final Set<SimpleEntry<String, String>> excludedPaths = new HashSet<>();
    private static final String ANY_METHOD = "ANY";

    // 생성자에서 예외 경로를 미리 등록합니다.
    public JwtAuthenticationFilter(JwtService jwtService, MemberRepository memberRepository) {
        this.jwtService = jwtService;
        this.memberRepository = memberRepository;

        /*admin dashboard*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/dashboard"));

        /*member 검색, 프로필*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/member"));
        /*회원가입, 로그인, 로그아웃*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/auth"));

        /*소켓 연결 나중에 interceptor 처리해야*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/stomp"));

        /*리뷰*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/review"));

        /*게시글*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/post"));
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/comments"));

        /*스토어*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/store"));

        /*팀*/
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/projectteams"));
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/team-applications"));
        excludedPaths.add(new SimpleEntry<>(ANY_METHOD, "/api/teammembers"));
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
                        requestPath.startsWith(pair.getValue()) && // .getSecond() 대신 .getValue() 사용
                                (pair.getKey().equalsIgnoreCase(method) || pair.getKey().equalsIgnoreCase(ANY_METHOD)) // .getFirst() 대신 .getKey() 사용
                );
    }
}
