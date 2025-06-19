package com.example.spring_hom_thu_dien_tu.controller;


import com.example.spring_hom_thu_dien_tu.model.EmailConfig;
import com.example.spring_hom_thu_dien_tu.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmailController {

    @Autowired
    private IEmailService emailService;

    private final List<String> languages = Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
    private final List<Integer> pageSizes = Arrays.asList(5, 10, 15, 25, 50, 100);


    @GetMapping("/email")
    public String showForm(Model model) {
        EmailConfig config = emailService.getCurrentConfig();
        model.addAttribute("emailConfig", config);
        model.addAttribute("languages", languages);
        model.addAttribute("pageSizes", pageSizes);
        return "form";
    }

    @PostMapping("/update")
    public String updateConfig(@ModelAttribute EmailConfig emailConfig, Model model) {
        emailService.update(emailConfig);
        model.addAttribute("emailConfig", emailConfig);
        return "result";
    }
}
