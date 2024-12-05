package com.example.project.responsitory;

import com.example.project.model.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentResponsitory extends JpaRepository<CommentsEntity,Long> {
    @Query("SELECT COUNT(c) FROM CommentsEntity c WHERE c.postId = :postId")
    Long countCommentsByPostId( Long postId);

    @Query("SELECT MAX(c.commentId) FROM CommentsEntity c ")
    Long findMaxCommentIdByPostId();
}
