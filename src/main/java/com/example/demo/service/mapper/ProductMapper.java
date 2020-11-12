package com.example.demo.service.mapper;

import com.example.demo.model.Product;
import com.example.demo.model.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product mapToProduct(ReviewDto reviewDto) {
        Product product = new Product();
        product.setExternalId(reviewDto.getProductId());
        return product;
    }
}
