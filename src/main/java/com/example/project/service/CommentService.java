package com.example.project.service;

import com.example.project.model.DTO.MapComment;
import com.example.project.model.entity.CommentsEntity;

import java.util.List;

public interface CommentService {

    List<MapComment> getAll(Long id);
    List<CommentsEntity> getAllComment();
    Long countCommentByPost(Long id);
    void saveComment(Long id,String content, Long postId, Long userId, Long parentId);
    Long maxId();

}
