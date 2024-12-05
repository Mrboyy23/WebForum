package com.example.project.model.DTO;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public class MapComment {
    private long commentId;
    private String content;
    private LocalDateTime createdAt;
    private Long parentId;
    private Long postId;
    private String name;
    private Long userId;
    private List<MapComment> mapComments ;

    public MapComment() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public MapComment(long commentId, String content, LocalDateTime createdAt, Long parentId, String name, List<MapComment> mapComments) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.name = name;
        this.mapComments = mapComments;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MapComment> getMapComments() {
        return mapComments;
    }

    public void setMapComments(List<MapComment> mapComments) {
        this.mapComments = mapComments;
    }
}
