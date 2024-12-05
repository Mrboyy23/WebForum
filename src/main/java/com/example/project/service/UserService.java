package com.example.project.service;

import com.example.project.model.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UsersEntity findByUsername(String username);
    UsersEntity findByEmail(String email);
    Optional<UsersEntity> findById(Long id);
    List<UsersEntity> getAll();
    List<UsersEntity> findUsersWhoSentOrReceivedMessages(Long id);
    public void save(UsersEntity usersEntity);
    Long idMax();
    public boolean validatePermission(String... roles);
    Boolean CheckUser(String username, String pass);
}
