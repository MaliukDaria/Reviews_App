package com.example.demo.service.db;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.model.ReviewDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.service.mapper.ProductMapper;
import com.example.demo.service.mapper.ReviewMapper;
import com.example.demo.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoDbDataSaver implements DbDataSaver<ReviewDto> {
    private static final Logger LOGGER = Logger.getLogger(ReviewDtoDbDataSaver.class);
    private static final String PASSWORD = "1111";
    private static final String ROLE_NAME = "USER";
    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;

    public ReviewDtoDbDataSaver(UserService userService, ProductService productService,
                                ReviewService reviewService, RoleService roleService,
                                UserMapper userMapper, ProductMapper productMapper,
                                ReviewMapper reviewMapper) {
        this.userService = userService;
        this.productService = productService;
        this.reviewService = reviewService;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void saveData(List<ReviewDto> reviewList) {
        long start = System.currentTimeMillis();
        LOGGER.info("saveData starts at " + start);
        addRolesIntoDb();
        List<User> users = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        for (ReviewDto reviewDto : reviewList) {
            User user = userMapper.mapToUser(reviewDto);
            user.setPassword(PASSWORD);
            user.setRoles(Set.of(roleService.getRoleByName(ROLE_NAME)));
            users.add(user);
            Product product = productMapper.mapToProduct(reviewDto);
            products.add(product);
            Review review = reviewMapper.mapToReview(reviewDto);
            review.setUser(user);
            review.setProduct(product);
            reviews.add(review);
        }
        userService.addAll(users);
        productService.addAll(products);
        reviewService.addAll(reviews);
        long end = System.currentTimeMillis();
        LOGGER.info("saveData ends at " + end + "\nstartReviewDtoDbDataSaver worked "
                + (end - start) / 1000 + " seconds");
    }

    private void addRolesIntoDb() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);
    }
}
