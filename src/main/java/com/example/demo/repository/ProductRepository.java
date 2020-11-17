package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    @Query("SELECT new com.example.demo.model.dto.ProductResponseDto(p.id) "
            + "FROM Review r "
            + "JOIN r.product p "
            + "GROUP BY p.id "
            + "ORDER BY COUNT(p) DESC")
    List<ProductResponseDto> getMostCommented(Pageable pageable);
}
