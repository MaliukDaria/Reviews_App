package com.example.demo.repository;

import com.example.demo.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Query("SELECT text FROM Review")
    List<String> getAllText();
}
