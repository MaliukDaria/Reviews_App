package com.example.demo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private Long score;
    private LocalDateTime dateTime;
    private String summary;
    @Column(length = 30_000)
    private String text;
}
