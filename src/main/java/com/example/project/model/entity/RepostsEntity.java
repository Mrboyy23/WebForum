package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "REPOSTS", schema = "SYSTEM", catalog = "")
public class RepostsEntity {
    private long repostId;
    private String content;
    private LocalDateTime createdAt;
    private Long postId;
    private Long userId;

    @Id
    @Column(name = "REPOST_ID")
    public long getRepostId() {
        return repostId;
    }

    public void setRepostId(long repostId) {
        this.repostId = repostId;
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
        RepostsEntity that = (RepostsEntity) o;
        return repostId == that.repostId && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repostId, content, createdAt);
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
