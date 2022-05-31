package com.codegym.module5_backend.model.dto;

import com.codegym.module5_backend.model.entity.Category;
import com.codegym.module5_backend.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
    private String name;

    private double price;

    private int quantity;

    private String description;

    private List<Image> images;

    private Category category;
}
