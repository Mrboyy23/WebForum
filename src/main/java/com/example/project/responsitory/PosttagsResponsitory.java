package com.example.project.responsitory;

import com.example.project.model.entity.PosttagsEntity;
import com.example.project.model.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PosttagsResponsitory extends JpaRepository<PosttagsEntity,Long> {
    @Query(value = "select t.name from TagsEntity t join PosttagsEntity p on t.tagId = p.tagId where p.postId = :id")
    List<String> getByPostId(Long id);
    @Query("SELECT MAX(c.postTagId) FROM PosttagsEntity c ")
    Long maxId();

}
