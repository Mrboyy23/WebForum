package com.example.project.service;

import com.example.project.model.entity.TagsEntity;

import java.util.List;

public interface TagService {
    List<TagsEntity> getAll();
    List<String> getByPostId(Long id);
    TagsEntity getByName(String name);
    Long idMax();
}
