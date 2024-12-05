package com.example.project.responsitory;

import com.example.project.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserResponsitory extends JpaRepository<UsersEntity,Long> {
    @Query(value = "select u from UsersEntity u where u.username like ?1")
    public UsersEntity findByUsername ( String username);
    @Query(value = "select u from UsersEntity u where u.email like ?1")
    public UsersEntity findByEmail ( String email);
    @Query("SELECT DISTINCT u FROM UsersEntity u " +
            "JOIN MessagesEntity m ON (m.senderId = u.userId OR m.receiverId = u.userId) " +
            "WHERE (m.senderId = ?1 OR m.receiverId = ?1) AND u.userId <> ?1")
    List<UsersEntity> findUsersWhoSentOrReceivedMessages(Long userId);
    @Query("SELECT MAX(c.userId) FROM UsersEntity c ")
    Long maxId();

}
