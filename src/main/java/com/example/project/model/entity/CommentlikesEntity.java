package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COMMENTLIKES", schema = "SYSTEM", catalog = "")
public class CommentlikesEntity {
    private long commentLikeId;
    private LocalDateTime createdAt;
    private Long commentId;
    private Long userId;

    @Id
    @Column(name = "COMMENT_LIKE_ID")
    public long getCommentLikeId() {
        return commentLikeId;
    }

    public void setCommentLikeId(long commentLikeId) {
        this.commentLikeId = commentLikeId;
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
        CommentlikesEntity that = (CommentlikesEntity) o;
        return commentLikeId == that.commentLikeId && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentLikeId, createdAt);
    }

    @Basic
    @Column(name = "COMMENT_ID")
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
