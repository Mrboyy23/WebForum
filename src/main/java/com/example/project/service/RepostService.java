package com.example.project.service;

import com.example.project.model.entity.PostsEntity;
import com.example.project.model.entity.RepostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepostService {
    void save(RepostsEntity repostsEntity);
    Long idMax();
    Page<RepostsEntity> getAll(Pageable pageable);
}
