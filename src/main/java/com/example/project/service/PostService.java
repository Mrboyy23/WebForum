package com.example.project.service;

import com.example.project.model.DTO.SavePort;
import com.example.project.model.entity.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    public PostsEntity getPostById(long id);
    List<PostsEntity> getPostByCategory(Long categoryId);
    void create(SavePort port);
    Boolean update(PostsEntity postsEntity);
    Boolean delete(Long id);
    Long increaseViewCount(Long postId);
    Page<PostsEntity> getAll(Pageable pageable);
    List<PostsEntity> recentPost();
    Long idMax();
}
