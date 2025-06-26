package com.example.form_validation.validators;

import com.example.form_validation.dtos.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            errors.rejectValue("firstName", "NotBlank", "First name is required!");
        } else if (userDTO.getFirstName().length() < 5 || userDTO.getFirstName().length() > 45) {
            errors.rejectValue("firstName", "Size", "First name must be between 5 and 45 characters!");
        }

        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            errors.rejectValue("lastName", "NotBlank", "Last name is required!");
        } else if (userDTO.getLastName().length() < 5 || userDTO.getLastName().length() > 45) {
            errors.rejectValue("lastName", "Size", "Last name must be between 5 and 45 characters!");
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.rejectValue("email", "NotBlank", "Email is required!");
        }

        if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().isEmpty()) {
            errors.rejectValue("phoneNumber", "NotBlank", "Phone number is required!");
        }

        if (userDTO.getAge() == null) {
            errors.rejectValue("age", "NotNull", "Age is required!");
        } else if (userDTO.getAge() < 18) {
            errors.rejectValue("age", "Min", "Age must be at least 18!");
        }
    }
}