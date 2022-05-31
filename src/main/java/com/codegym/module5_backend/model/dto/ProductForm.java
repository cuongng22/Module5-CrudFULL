package com.codegym.module5_backend.model.dto;

import com.codegym.module5_backend.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String name;

    private double price;

    private int quantity;

    private String description;

    private MultipartFile[] image;

    private Category category;
}
