package com.example.blog.services.user;

public interface IUserService {
    void registerUser(String username, String rawPassword, String email);
}
