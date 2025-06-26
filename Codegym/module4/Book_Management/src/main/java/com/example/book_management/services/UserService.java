package com.example.book_management.services;

import com.example.book_management.models.Role;
import com.example.book_management.models.User;
import com.example.book_management.repositories.IRoleRepository;
import com.example.book_management.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService implements IUserService{
    @Autowired private IUserRepository userRepo;
    @Autowired private IRoleRepository roleRepo;
    @Autowired private PasswordEncoder encoder;

    public void registerUser(String username, String rawPassword, String email) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

        if (userRepo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        Role roleUser = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encoder.encode(rawPassword));
        user.getRoles().add(roleUser);
        userRepo.save(user);
    }

}

