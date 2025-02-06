package com.codebase.backend.chat.repository;

import com.codebase.backend.chat.dto.MemberChatroomMapping;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public List<MemberChatroomMapping> findAllByMemberId(@Param("memberId") int memberId) {
        return sql.selectList("memberchatroom.findAllByMemberId", memberId);
    }

    public int countMemberByChatroomId(int chatroomId) {
        return sql.selectOne("memberchatroom.countMemberByChatroomId", chatroomId);
    }

    public List<MemberChatroomMapping> findByChatroomId(int chatroomId) {
        return sql.selectList("memberchatroom.findByChatroomId", chatroomId);
    }

    public void updateLastCheckedByMemberIdAndChatroomId(int memberId, int chatroomId) {
        sql.update("memberchatroom.updateLastCheckedByMemberIdAndChatroomId", Map.of("memberId", memberId, "chatroomId", chatroomId, "checked", LocalDateTime.now()));
    }

    public Integer findByTwoMemberId(int memberId1, int memberId2) {
        return sql.selectOne("memberchatroom.findByTwoMemberId", Map.of("memberId1", memberId1, "memberId2", memberId2));
    }

    public void saveAll(List<MemberChatroomMapping> memberChatroomMappings) {
        sql.insert("memberchatroom.saveAll", memberChatroomMappings);
    }

    public List<Integer> findMembersByChatroomId(int chatroomId) {
        return sql.selectList("memberchatroom.findMembersByChatroomId", Map.of("chatroomId", chatroomId));
    }
}