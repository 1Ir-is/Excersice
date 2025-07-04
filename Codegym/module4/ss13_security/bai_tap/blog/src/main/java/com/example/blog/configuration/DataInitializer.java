package com.example.blog.configuration;

import com.example.blog.models.Role;
import com.example.blog.models.User;
import com.example.blog.repositories.IRoleRepository;
import com.example.blog.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IUserRepository userRepo;
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
            admin.setEmail("admin@example.com");
            admin.setEnabled(true);
            admin.getRoles().add(roleAdmin);
            userRepo.save(admin);
        }
    }
}