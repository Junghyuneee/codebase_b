package com.codebase.backend.chat.repository;


import com.codebase.backend.chat.dto.Chatroom;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatroomRepository {

    private final SqlSessionTemplate sql;

    public Chatroom save(Chatroom chatroom) {
        sql.insert("chatroom.save", chatroom);
        return chatroom;
    }

    public void setDM(Chatroom chatroom){
        sql.update("chatroom.setDM", chatroom);
    }

    public Chatroom findById(int chatroomId) {
        return sql.selectOne("chatroom.findById", chatroomId);
    }


    public void deleteById(int chatroomId) {
        sql.delete("chatroom.deleteById", chatroomId);
    }
}
