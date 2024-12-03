package com.codebase.backend.chat.repository;

import com.codebase.backend.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final SqlSessionTemplate sql;

    public ChatMessage save(ChatMessage chatMessage) {
        sql.insert("message.save", chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findAllByChatroomId(int chatroomId) {
        return sql.selectList("message.findAllByChatroomId", chatroomId);
    }

    public Boolean existsByChatroomIdAndCreatedAtAfter(int chatroomId, LocalDateTime createdAt) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatroomId", chatroomId);
        params.put("createdAt", createdAt);
        return sql.selectOne("message.existsByChatroomIdAndCreatedAtAfter", params);
    }
}
