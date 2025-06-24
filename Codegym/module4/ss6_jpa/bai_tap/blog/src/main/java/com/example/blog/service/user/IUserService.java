package com.example.blog.service.user;

import com.example.blog.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
}