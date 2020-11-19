package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductResponseDto;
import java.util.List;

public interface ProductService extends GenericService<Product> {
    List<ProductResponseDto> getMostCommented(int numberOfProducts);
}
