package com.example.project.responsitory;

import com.example.project.model.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryResponsitory extends JpaRepository<CategoriesEntity,Long> {
}
