package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/add")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }
}