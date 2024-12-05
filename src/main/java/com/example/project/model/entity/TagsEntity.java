package com.example.project.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TAGS", schema = "SYSTEM", catalog = "")
public class TagsEntity {
    private long tagId;
    private String name;

    @Id
    @Column(name = "TAG_ID")
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagsEntity that = (TagsEntity) o;
        return tagId == that.tagId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, name);
    }
}
