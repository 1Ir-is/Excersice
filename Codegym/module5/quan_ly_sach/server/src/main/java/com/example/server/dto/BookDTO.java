package com.example.server.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    @NotBlank(message = "Mã sách không được để trống!")
    @Pattern(regexp = "BO-\\d{4}$", message = "Mã sách phải theo định dạng BO-XXXX")
    private String bookCode;

    @NotBlank(message = "Tên sách không được để trống!")
    @Size(max = 100, message = "Tên sách không được dài quá 100 ký tự")
    private String title;


    private Long categoryId;
    private String categoryName;

    @NotNull(message = "Ngày nhập không được để trống!")
    @PastOrPresent(message = "Ngày nhập không được lớn hơn ngày hiện tại")
    private LocalDate importDate;

    @NotNull(message = "Số lượng sách không được để trống!")
    @Min(value = 1, message = "Số lượng sách phải lớn hơn 0")
    private Integer quantity;
}
