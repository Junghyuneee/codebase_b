package com.codebase.backend.admin.service;

import com.codebase.backend.admin.repository.MemberAndChatRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import com.codebase.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberAndChatService {

    private final MemberAndChatRepository memberAndChatRepository;
    private final MemberRepository memberRepository;

    public Map<String, Object> getAllMembers(int page, int size) {
        int offset = (page - 1) * size;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

        params.put("offset", offset);
        params.put("limit", size);
        int totalData = memberAndChatRepository.countAllMembers();

        List<Member> members = memberAndChatRepository.getAllMembers(params);
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            memberDTOs.add(MemberDTO.from(member));
        }

        response.put("data", memberDTOs);
        response.put("totalPages", (int) Math.ceil((double) totalData / size));
        response.put("currentPage", page);

        return response;
    }

    public String changeRole(int memberId) {
        Member member = memberRepository.findById(memberId);
        boolean role = !member.isRole();

        Map<String, Object> params = new HashMap<>();
        params.put("id", memberId);
        params.put("role", role);
        memberAndChatRepository.changeRole(params);

        return member.getName();
    }

}
