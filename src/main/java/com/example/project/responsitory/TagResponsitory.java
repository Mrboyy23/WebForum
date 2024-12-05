package com.example.project.responsitory;

import com.example.project.model.entity.PosttagsEntity;
import com.example.project.model.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagResponsitory extends JpaRepository<TagsEntity,Long> {
    @Query(value = "select t from TagsEntity t where t.name = :name")
    TagsEntity getByName(String name);
    @Query("SELECT MAX(c.tagId) FROM TagsEntity c ")
    Long maxId();
}
