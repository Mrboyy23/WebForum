package com.example.project.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "POSTTAGS", schema = "SYSTEM", catalog = "")
public class PosttagsEntity {
    private long postTagId;
    private Long postId;
    private Long tagId;

    @Id
    @Column(name = "POST_TAG_ID")
    public long getPostTagId() {
        return postTagId;
    }

    public void setPostTagId(long postTagId) {
        this.postTagId = postTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosttagsEntity that = (PosttagsEntity) o;
        return postTagId == that.postTagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postTagId);
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
    @Column(name = "TAG_ID")
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
