package com.codebase.backend.member.service;

import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.exception.UserAlreadyExistsException;
import com.codebase.backend.member.response.post.UserAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
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

    public UserAuthenticationResponse login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        if(passwordEncoder.matches(password, member.getPassword())){
            String accessToken = jwtService.generateAccessToken(member);
            System.out.println("accessToken = " + accessToken);
            return new UserAuthenticationResponse(accessToken);
        } else{
            return new UserAuthenticationResponse("Invalid password");
        }
    }
}
