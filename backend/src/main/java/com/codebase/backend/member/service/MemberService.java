package com.codebase.backend.member.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.response.exception.UserAlreadyExistsException;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import com.codebase.backend.project.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) memberRepository.findByEmail(email);
    }

    // 회원가입
    public Member create(MemberSignUpRequestBody memberSignUpRequestBody) {
        if(memberRepository.findByEmail(memberSignUpRequestBody.email()) != null){
            throw new UserAlreadyExistsException(memberSignUpRequestBody.email());
        }
        
        int cart_id = cartService.insertCart(memberSignUpRequestBody.email());
    
        Member member = Member.builder()
                .email(memberSignUpRequestBody.email())
                .name(memberSignUpRequestBody.name())
                .addr(memberSignUpRequestBody.addr())
                .postcode(memberSignUpRequestBody.postcode())
                .password(passwordEncoder.encode(memberSignUpRequestBody.password()))
                .tel(memberSignUpRequestBody.tel())
                .createdAt(LocalDate.now())
                .role(false)
                .projectCount(3)
                .cart_id(cart_id)
                .build();

        memberRepository.save(member);

        return member;
    }

    // 회원정보 수정, 소셜 회원가입
    public Member update(MemberSignUpRequestBody memberSignUpRequestBody) {
        if(memberRepository.findByEmail(memberSignUpRequestBody.email()) == null){
            throw new UsernameNotFoundException(memberSignUpRequestBody.email());
        }

        Member member = memberRepository.findByEmail(memberSignUpRequestBody.email());
        member.setName(memberSignUpRequestBody.name());
        member.setAddr(memberSignUpRequestBody.addr());
        member.setPostcode(memberSignUpRequestBody.postcode());
        member.setTel(memberSignUpRequestBody.tel());
        
        memberRepository.update(member);

        return member;
    }

    // 로그인
    public UserAuthenticationResponse login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        if(passwordEncoder.matches(password, member.getPassword())){
            String accessToken = jwtService.generateAccessToken(member);
            return new UserAuthenticationResponse(accessToken, member.getEmail(), member.getName(), member.getId(), member.getProjectCount());
        } else{
            return new UserAuthenticationResponse("Invalid password", null, null, -1, -1);
        }
    }

    // 검색
    public List<Member> searchMember(String name) {
        return memberRepository.searchByName(name);
    }

    // ID로 회원 조회하는 메소드 추가
    public Member getMemberById(Integer id) {
        return memberRepository.findById(id);
    }
}
