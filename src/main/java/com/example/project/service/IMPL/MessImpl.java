package com.example.project.service.IMPL;

import com.example.project.model.entity.MessagesEntity;
import com.example.project.responsitory.MessResponsitory;
import com.example.project.service.MessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessImpl implements MessService {
    private MessResponsitory messResponsitory;
    @Override
    public List<MessagesEntity> getAllWithFriend(Long me, Long you) {
        return messResponsitory.getAllWithFriend(me,you);
    }

    @Override
    public void saveMessage(MessagesEntity message) {
        messResponsitory.save(message);
    }

    @Override
    public Long maxId() {
        return messResponsitory.idmax();
    }
}
