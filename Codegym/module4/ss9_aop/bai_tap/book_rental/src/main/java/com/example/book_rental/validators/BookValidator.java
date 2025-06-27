package com.example.book_rental.validators;

import com.example.book_rental.dtos.BookDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BookDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDTO bookDTO = (BookDTO) target;

        if (bookDTO.getName() == null || bookDTO.getName().isEmpty()) {
            errors.rejectValue("name", "NotBlank", "Book name is required!");
        } else if (bookDTO.getName().length() > 255) {
            errors.rejectValue("name", "Size", "Book name must not exceed 255 characters!");
        } else if (!bookDTO.getName().matches("^[a-zA-Z0-9 ]+$")) {
            errors.rejectValue("name", "Pattern", "Book name must not contain special characters!");
        }

        if (bookDTO.getQuantity() == null) {
            errors.rejectValue("quantity", "NotNull", "Quantity is required!");
        } else if (bookDTO.getQuantity() < 0) {
            errors.rejectValue("quantity", "Min", "Quantity must not be negative!");
        } else if (bookDTO.getQuantity() > 10000) {
            errors.rejectValue("quantity", "Max", "Quantity must not exceed 10,000!");
        }
    }
}
