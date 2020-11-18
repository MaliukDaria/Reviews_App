package com.example.demo.controller;

import com.example.demo.model.dto.ProductResponseDto;
import com.example.demo.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String NUMBER_OF_PRODUCTS = "1000";
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/most-commented")
    public List<ProductResponseDto> getMostCommentedProducts(
            @RequestParam(defaultValue = NUMBER_OF_PRODUCTS) int numberOfProducts) {
        return productService.getMostCommented(numberOfProducts);
    }
}
