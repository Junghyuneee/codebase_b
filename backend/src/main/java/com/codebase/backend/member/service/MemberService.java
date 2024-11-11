package com.codebase.backend.member.service;

import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.repository.MemberRepository;
import com.codebase.backend.member.response.post.MemberSignUpRequestBody;
import com.codebase.backend.member.response.post.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public MemberDTO create(MemberSignUpRequestBody memberSignUpRequestBody) {
        if(memberRepository.findByEmail(memberSignUpRequestBody.email()) != null){
            throw new UserAlreadyExistsException(memberSignUpRequestBody.email());
        }

        MemberDTO member = MemberDTO.builder()
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
}
