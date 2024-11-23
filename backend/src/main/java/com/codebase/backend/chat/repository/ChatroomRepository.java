package com.codebase.backend.chat.repository;


import com.codebase.backend.chat.dto.Chatroom;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatroomRepository {

    private final SqlSessionTemplate sql;

    public Chatroom save(Chatroom chatroom) {
        sql.insert("chatroom.save", chatroom);
        return chatroom;
    }

    public Chatroom findById(int chatroomId) {
        return sql.selectOne("chatroom.findById", chatroomId);
    }

    public List<Chatroom> findAllByIds(List<Integer> chatroomIds) {
        return sql.selectList("chatroom.findAllByIds", chatroomIds);
    }
}
