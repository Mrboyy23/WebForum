package com.example.project.responsitory;

import com.example.project.model.entity.PostsEntity;
import com.example.project.model.entity.RepostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepostResponsitory extends JpaRepository<RepostsEntity,Long> {
    @Query("SELECT MAX(c.repostId) FROM RepostsEntity c ")
    Long maxId();

    @Query("SELECT p FROM RepostsEntity p ORDER BY p.createdAt DESC")
    Page<RepostsEntity> findAllRepost(Pageable pageable);
}
