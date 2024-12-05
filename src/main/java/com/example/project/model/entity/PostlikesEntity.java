package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "POSTLIKES", schema = "SYSTEM", catalog = "")
public class PostlikesEntity {
    private long postLikeId;
    private LocalDateTime createdAt;
    private Long postId;
    private Long userId;

    @Id
    @Column(name = "POST_LIKE_ID")
    public long getPostLikeId() {
        return postLikeId;
    }

    public void setPostLikeId(long postLikeId) {
        this.postLikeId = postLikeId;
    }

    @Basic
    @Column(name = "CREATED_AT")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostlikesEntity that = (PostlikesEntity) o;
        return postLikeId == that.postLikeId && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postLikeId, createdAt);
    }

    @Basic
    @Column(name = "POST_ID")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
