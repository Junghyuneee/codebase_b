package com.codebase.backend.chat.repository;

import com.codebase.backend.chat.dto.Chatroom;
import com.codebase.backend.chat.dto.MemberChatroomMapping;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberChatroomMappingRepository {
    private final SqlSessionTemplate sql;

    public MemberChatroomMapping save(MemberChatroomMapping memberChatroomMapping) {
        sql.insert("memberchatroom.save", memberChatroomMapping);
        return memberChatroomMapping;
    }

    public Boolean existsByMemberIdAndChatroomId(@Param("memberId") int memberId, @Param("chatroomId") int chatroomId) {
        return sql.selectOne("memberchatroom.existsByMemberIdAndChatroomId", Map.of("memberId", memberId, "chatroomId", chatroomId));
    }

    public void deleteByMemberIdAndChatroomId(@Param("memberId") int memberId, @Param("chatroomId") int chatroomId) {
        sql.delete("memberchatroom.deleteByMemberIdAndChatroomId", Map.of("memberId", memberId, "chatroomId", chatroomId));
    }

    public List<Integer> findChatroomIdsByMemberId(@Param("memberId") int memberId) {
        return sql.selectList("memberchatroom.findAllByMemberId", memberId);
    }
}
