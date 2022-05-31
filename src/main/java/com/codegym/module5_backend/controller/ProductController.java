package com.codegym.module5_backend.controller;

import com.codegym.module5_backend.model.dto.ProductForm;
import com.codegym.module5_backend.model.dto.ProductListDto;
import com.codegym.module5_backend.model.entity.Category;
import com.codegym.module5_backend.model.entity.Image;
import com.codegym.module5_backend.model.entity.Product;
import com.codegym.module5_backend.service.category.ICategoryService;
import com.codegym.module5_backend.service.image.IImageService;
import com.codegym.module5_backend.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IImageService iImageService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/list")
    public ResponseEntity<List<ProductListDto>> findAllProduct(@RequestParam(name = "q")Optional<String> q, @PageableDefault(value = 5)Pageable pageable) {
        Page<Product> products;
        List<ProductListDto> productList = new ArrayList<>();
        if (!q.isPresent()) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findByName(q.get(), pageable);
        }
        for (int i = 0; i < products.getContent().size(); i++) {
            productList.add(new ProductListDto(
                    products.getContent().get(i).getName(),
                    products.getContent().get(i).getPrice(),
                    products.getContent().get(i).getQuantity(),
                    products.getContent().get(i).getDescription(),
                    this.iImageService.findImageByProduct(products.getContent().get(i).getId()),
                    products.getContent().get(i).getCategory()
            ));
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductListDto> findOneById(@PathVariable Long id) {
        Product product = productService.findById(id).get();
        List<Image> images = iImageService.findImageByProduct(id);
        ProductListDto productDto = new ProductListDto(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                images,
                product.getCategory()
        );
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAllCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createNewProduct(@ModelAttribute ProductForm productForm) {
        Product product = new Product(
          productForm.getName(),
          productForm.getPrice(),
          productForm.getQuantity(),
          productForm.getDescription(),
          productForm.getCategory()
        );
        productService.save(product);
        for (int i = 0; i < productForm.getImage().length; i++) {
            iImageService.save(
                    new Image(
                            productForm.getImage()[i].getOriginalFilename(),
                            product
                    )
            );
            try {
                FileCopyUtils.copy(productForm.getImage()[i].getBytes(), new File(uploadPath +productForm.getImage()[i].getOriginalFilename() ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iImageService.deleteImageByProductId(id);
        productService.removeById(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
