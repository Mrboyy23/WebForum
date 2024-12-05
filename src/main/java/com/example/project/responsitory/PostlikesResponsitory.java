package com.example.project.responsitory;

import com.example.project.model.entity.PostlikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostlikesResponsitory extends JpaRepository<PostlikesEntity,Long> {
    PostlikesEntity save(PostlikesEntity postlikesEntity);

    @Query("SELECT COUNT(p) FROM PostlikesEntity p WHERE p.postId = :postId AND p.userId = :userId")
    Long countLikesByPostIdAndUserId(Long postId,Long userId);

    @Modifying
    @Query("DELETE FROM PostlikesEntity p WHERE p.postId = :postId AND p.userId = :userId")
    @Transactional
    void deleteLike(Long postId, Long userId);

    @Query("SELECT COUNT(p) FROM PostlikesEntity p WHERE p.postId = :postId")
    Long countLikesByPostId(Long postId);

    @Query("SELECT p.userId FROM PostlikesEntity p WHERE p.postId = :postId")
    List<Long> findUserIdsByPostId(Long postId);

    @Query("SELECT MAX(c.postLikeId) FROM PostlikesEntity c ")
    Long maxId();

}
