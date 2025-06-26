package com.example.form_validation.controllers;

import com.example.form_validation.dtos.UserDTO;
import com.example.form_validation.services.IUserService;
import com.example.form_validation.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FormController {
    @Autowired
    private IUserService userService;

    private final UserValidator userValidator = new UserValidator();

    @GetMapping
    public String showForm(final Model model) {
        final UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "index";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("userDTO") final UserDTO userDTO, final BindingResult bindingResult, final Model model) {
        this.userValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "index";
        }
        this.userService.save(userDTO);
        model.addAttribute("user", userDTO);
        return "result";
    }
}