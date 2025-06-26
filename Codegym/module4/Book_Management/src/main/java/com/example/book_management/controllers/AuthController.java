package com.example.book_management.controllers;

import com.example.book_management.models.User;
import com.example.book_management.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired private IUserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String confirmPassword,
                                  RedirectAttributes redirect) {
        if (!password.equals(confirmPassword)) {
            redirect.addFlashAttribute("error", "Mật khẩu xác nhận không khớp!");
            return "redirect:/register";
        }

        try {
            userService.registerUser(username, password, email);
            redirect.addFlashAttribute("success", "Đăng ký thành công!");
            return "redirect:/login";
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }



    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
