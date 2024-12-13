package com.codebase.backend.admin.service;

import com.codebase.backend.admin.repository.MemberAndChatRepository;
import com.codebase.backend.member.dto.Member;
import com.codebase.backend.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberAndChatService {

    @Autowired
    private MemberAndChatRepository memberAndChatRepository;

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

}
