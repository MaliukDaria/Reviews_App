package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductResponseDto;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int NUMBER_OF_MOST_COMMENTED_PRODUCTS = 10;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void addAll(List<Product> products) {
        productRepository.saveAll(products);
    }


    @Override
    public List<ProductResponseDto> getMostCommented(int numberOfProducts) {
        return productRepository.getMostCommented(PageRequest.of(0, numberOfProducts));
    }
}
