package com.example.song_validator.validators;

import com.example.song_validator.dtos.SongDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SongValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SongDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongDTO songDTO = (SongDTO) target;

        if (songDTO.getName() == null || songDTO.getName().isEmpty()) {
            errors.rejectValue("name", "NotBlank", "Song name is required!");
        } else if (songDTO.getName().length() > 800) {
            errors.rejectValue("name", "Size", "Song name must not exceed 800 characters!");
        } else if (!songDTO.getName().matches("^[a-zA-Z0-9 ]+$")) {
            errors.rejectValue("name", "Pattern", "Song name must not contain special characters!");
        }

        if (songDTO.getArtist() == null || songDTO.getArtist().isEmpty()) {
            errors.rejectValue("artist", "NotBlank", "Artist name is required!");
        } else if (songDTO.getArtist().length() > 300) {
            errors.rejectValue("artist", "Size", "Artist name must not exceed 300 characters!");
        } else if (!songDTO.getArtist().matches("^[a-zA-Z0-9 ]+$")) {
            errors.rejectValue("artist", "Pattern", "Artist name must not contain special characters!");
        }

        if (songDTO.getGenre() == null || songDTO.getGenre().isEmpty()) {
            errors.rejectValue("genre", "NotBlank", "Genre name is required!");
        } else if (songDTO.getGenre().length() > 1000) {
            errors.rejectValue("genre", "Size", "Genre name must not exceed 1000 characters!");
        } else if (!songDTO.getGenre().matches("^[a-zA-Z0-9, ]+$")) {
            errors.rejectValue("genre", "Pattern", "Genre name must not contain special characters (except ',')!");
        }
    }
}