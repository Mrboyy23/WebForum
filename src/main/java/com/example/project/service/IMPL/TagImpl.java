package com.example.project.service.IMPL;

import com.example.project.model.entity.TagsEntity;
import com.example.project.responsitory.PosttagsResponsitory;
import com.example.project.responsitory.TagResponsitory;
import com.example.project.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagImpl implements TagService {
    private TagResponsitory tagResponsitory;
    private PosttagsResponsitory posttagsResponsitory;

    @Override
    public List<TagsEntity> getAll() {
        return tagResponsitory.findAll();
    }

    @Override
    public List<String> getByPostId(Long id) {
        return posttagsResponsitory.getByPostId(id);
    }

    @Override
    public TagsEntity getByName(String name) {
       return tagResponsitory.getByName(name);
    }

    @Override
    public Long idMax() {
        return tagResponsitory.maxId();
    }
}
