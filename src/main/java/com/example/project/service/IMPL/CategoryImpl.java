package com.example.project.service.IMPL;

import com.example.project.model.entity.CategoriesEntity;
import com.example.project.responsitory.CategoryResponsitory;
import com.example.project.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {
    private CategoryResponsitory categoryResponsitory;

    @Override
    public List<CategoriesEntity> getAll() {
        return categoryResponsitory.findAll();
    }
}
