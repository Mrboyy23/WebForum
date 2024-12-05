package com.example.project.responsitory;

import com.example.project.model.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessResponsitory extends JpaRepository<MessagesEntity,Long> {
    @Query(value = "SELECT m from MessagesEntity m where (m.senderId = :a and m.receiverId = :b) or (m.senderId = :b and m.receiverId = :a) " )
    List<MessagesEntity> getAllWithFriend(Long a, Long b);
    @Query("SELECT MAX(c.messageId) FROM MessagesEntity c ")
    Long idmax();
}
