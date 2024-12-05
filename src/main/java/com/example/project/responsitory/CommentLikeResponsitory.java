package com.example.project.responsitory;

import com.example.project.model.entity.CommentlikesEntity;
import com.example.project.model.entity.PostlikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentLikeResponsitory extends JpaRepository<CommentlikesEntity, Long> {


    @Query("SELECT COUNT(p) FROM CommentlikesEntity p WHERE p.commentId = :commentId AND p.userId = :userId")
    Long countLikesByCommentIdAndUserId(Long commentId,Long userId);

    @Modifying
    @Query("DELETE FROM CommentlikesEntity p WHERE p.commentId = :commentId AND p.userId = :userId")
    @Transactional
    void deleteLike(Long commentId, Long userId);

    @Query("SELECT MAX(c.commentLikeId) FROM CommentlikesEntity c ")
    Long maxId();
}
