package com.example.project.responsitory;

import com.example.project.model.entity.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostResponsitory extends JpaRepository<PostsEntity, Long> {
    @Query(value = "select p from PostsEntity p where p.postId = ?1")
    PostsEntity getPostById(long id);

    @Query(value = "select p from PostsEntity p order by p.createdAt desc")
    List<PostsEntity> recentPost(Pageable pageable);

    @Query(value = "select p from PostsEntity p where p.categoryId = :categoryid")
    List<PostsEntity> getPostByCategory(Long categoryid);
    @Query("SELECT MAX(c.postId) FROM PostsEntity c ")
    Long maxId();
    @Query("SELECT p FROM PostsEntity p ORDER BY p.createdAt DESC")
    Page<PostsEntity> findAllSortedByCreatedAtDesc(Pageable pageable);

}
