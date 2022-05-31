package com.codegym.module5_backend.service.product;

import com.codegym.module5_backend.model.entity.Product;
import com.codegym.module5_backend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findByName(String name, Pageable pageable);
}
