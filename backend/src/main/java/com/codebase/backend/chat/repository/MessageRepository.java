package com.codebase.backend.chat.repository;

import com.codebase.backend.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final SqlSessionTemplate sql;

    public ChatMessage save(ChatMessage chatMessage) {
        sql.insert("message.save", chatMessage);
        return chatMessage;
    }
}
