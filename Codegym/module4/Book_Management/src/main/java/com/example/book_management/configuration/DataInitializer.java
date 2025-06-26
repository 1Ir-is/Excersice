package com.example.book_management.configuration;

import com.example.book_management.models.Role;
import com.example.book_management.models.User;
import com.example.book_management.repositories.IRoleRepository;
import com.example.book_management.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired private IRoleRepository roleRepo;
    @Autowired private IUserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        Role roleAdmin = roleRepo.findByName("ROLE_ADMIN").orElseGet(() ->
                roleRepo.save(new Role(null, "ROLE_ADMIN")));
        Role roleUser = roleRepo.findByName("ROLE_USER").orElseGet(() ->
                roleRepo.save(new Role(null, "ROLE_USER")));

        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.getRoles().add(roleAdmin);
            userRepo.save(admin);
        }
    }
}

