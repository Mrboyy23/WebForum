package com.example.project.service.IMPL;

import com.example.project.model.entity.RepostsEntity;
import com.example.project.responsitory.RepostResponsitory;
import com.example.project.service.RepostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepostImpl implements RepostService {
    private RepostResponsitory repostResponsitory;

    @Override
    public void save(RepostsEntity repostsEntity) {
       repostResponsitory.save(repostsEntity);
    }

    @Override
    public Long idMax() {
        return repostResponsitory.maxId();
    }

    @Override
    public Page<RepostsEntity> getAll(Pageable pageable) {
        return repostResponsitory.findAll(pageable);
    }
}
