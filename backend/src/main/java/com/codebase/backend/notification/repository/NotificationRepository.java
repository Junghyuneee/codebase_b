package com.codebase.backend.notification.repository;

import com.codebase.backend.notification.dto.Notification;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {

    private final SqlSessionTemplate sql;

    // save
    public Notification save(Notification notification) {
        sql.insert("notification.save", notification);
        return notification;
    }

    // readlist
    public List<Notification> findByMemberId(int member) {
        return sql.selectList("notification.findByMemberId", member);
    }

    // read
    public void readNoti(List<Long> ids) {
        sql.update("notification.readNoti", ids);
    }

    //    count unreadNotis
    public Integer countUnreadNotifications(int member) {
        return sql.selectOne("notification.countUnreadNotifications", member);
    }

    // delete
    public void delete(Long id) {
        sql.delete("notification.deleteById", id);
    }
}
