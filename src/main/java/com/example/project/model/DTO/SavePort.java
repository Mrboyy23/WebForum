package com.example.project.model.DTO;

import com.example.project.model.entity.TagsEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SavePort {
    private long postId;
    private String content;
    private String title;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long repostCount ;
    private Long userId;
    private Long categoryId;
    private String tags;

    public SavePort() {
    }

    public SavePort(long postId, String content, String title, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Long repostCount, Long userId, Long categoryId, String tags) {
        this.postId = postId;
        this.content = content;
        this.title = title;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.repostCount = repostCount;
        this.userId = userId;
        this.categoryId = categoryId;
        this.tags = tags;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(Long repostCount) {
        this.repostCount = repostCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
