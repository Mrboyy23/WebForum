package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "POSTS", schema = "SYSTEM", catalog = "")
public class PostsEntity {
    private long postId;
    private String content;
    private String title;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long repostCount;
    private Long userId;
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "POST_ID")
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "REPOST_COUNT")
    public Long getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(Long repostCount) {
        this.repostCount = repostCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsEntity that = (PostsEntity) o;
        return postId == that.postId && Objects.equals(content, that.content) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(repostCount, that.repostCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, content, imageUrl, createdAt, updatedAt, repostCount);
    }

    @Basic
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CATEGORY_ID")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
