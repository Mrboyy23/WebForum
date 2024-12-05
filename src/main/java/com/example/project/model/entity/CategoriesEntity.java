package com.example.project.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CATEGORIES", schema = "SYSTEM", catalog = "")
public class CategoriesEntity {
    private long categoryId;
    private Long parentCategory;
    private String name;
    private String description;

    @Id
    @Column(name = "CATEGORY_ID")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "PARENT_CATEGORY")
    public Long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return categoryId == that.categoryId && Objects.equals(parentCategory, that.parentCategory) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, parentCategory, name, description);
    }
}
