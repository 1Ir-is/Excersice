package com.example.form_validation.services;

import com.example.form_validation.dtos.UserDTO;
import com.example.form_validation.models.User;

public interface IUserService {
    User save(UserDTO userDTO);
}
