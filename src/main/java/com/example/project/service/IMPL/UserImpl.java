package com.example.project.service.IMPL;

import com.example.project.model.entity.UsersEntity;
import com.example.project.responsitory.UserResponsitory;
import com.example.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {
    private UserResponsitory userResponsitory;
    @Override
    public UsersEntity findByUsername(String username) {
       return   userResponsitory.findByUsername(username);
    }

    @Override
    public UsersEntity findByEmail(String email) {
        return userResponsitory.findByEmail(email);
    }

    @Override
    public Optional<UsersEntity> findById(Long id) {
        return userResponsitory.findById(id);
    }

    @Override
    public List<UsersEntity> getAll() {
        return userResponsitory.findAll();
    }

    @Override
    public List<UsersEntity> findUsersWhoSentOrReceivedMessages(Long id) {
        return userResponsitory.findUsersWhoSentOrReceivedMessages(id);
    }

    @Override
    public void save(UsersEntity usersEntity) {
        userResponsitory.save(usersEntity);
    }

    @Override
    public Long idMax() {
        return userResponsitory.maxId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity entity = findByUsername(username);
        if (entity != null) {
            String role = (entity.getRole() == 1) ? "ADMIN" : "USER"; // Xác định quyền dựa trên quyen
            return User.withUsername(entity.getUsername())
                    .password(entity.getPasswordHash())
                    .roles(role) // Thiết lập vai trò
                    .build();
        }
        throw new UsernameNotFoundException("Username not found!");
    }
    @Override
    public boolean validatePermission(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false; // Không có quyền
        }

        UsersEntity user = (UsersEntity) authentication.getPrincipal(); // Lấy thông tin người dùng
        Long userRole = user.getRole();

        // Kiểm tra quyền
        for (String role : roles) {
            if ((userRole == 1 && role.equals("ADMIN")) ||
                    (userRole != 1 && role.equals("USER"))) {
                return true; // Có quyền
            }
        }
        return false; // Không có quyền
    }
    @Override
    public Boolean CheckUser(String username, String pass) {
        UsersEntity usersEntity = userResponsitory.findByUsername(username);
        if (usersEntity != null && usersEntity.getPasswordHash().equals(pass)) {
            return true;
        }

        return false;
    }
}
