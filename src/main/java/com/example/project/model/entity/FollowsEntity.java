package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "FOLLOWS", schema = "SYSTEM", catalog = "")
public class FollowsEntity {
    private long followId;
    private Time createdAt;
    private Long followerId;
    private Long followedId;

    @Id
    @Column(name = "FOLLOW_ID")
    public long getFollowId() {
        return followId;
    }

    public void setFollowId(long followId) {
        this.followId = followId;
    }

    @Basic
    @Column(name = "CREATED_AT")
    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowsEntity that = (FollowsEntity) o;
        return followId == that.followId && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, createdAt);
    }

    @Basic
    @Column(name = "FOLLOWER_ID")
    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    @Basic
    @Column(name = "FOLLOWED_ID")
    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }
}
