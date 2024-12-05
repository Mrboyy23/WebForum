package com.example.project.service;

import com.example.project.model.entity.MessagesEntity;

import java.util.List;

public interface MessService {
    List<MessagesEntity> getAllWithFriend(Long me, Long you);
    void saveMessage(MessagesEntity message);
    Long maxId();
}
