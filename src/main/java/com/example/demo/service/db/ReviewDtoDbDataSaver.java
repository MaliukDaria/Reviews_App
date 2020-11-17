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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, User> users = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        List<Review> reviews = new ArrayList<>();
        Role userRole = roleService.getRoleByName(ROLE_NAME);
        for (ReviewDto reviewDto : reviewList) {
            User user = userMapper.mapToUser(reviewDto);
            user.setPassword(PASSWORD);
            user.setRoles(Set.of(userRole));
            User userFromMap = users.get(user.getExternalId());
            if (userFromMap != null) {
                user.setId(userFromMap.getId());
            }
            User userFromDb = userService.add(user);
            users.put(userFromDb.getExternalId(), userFromDb);
            Product product = productMapper.mapToProduct(reviewDto);
            Product productFromMap = products.get(product.getExternalId());
            if (productFromMap != null) {
                product.setId(productFromMap.getId());
            }
            Product productFromDb = productService.add(product);
            products.put(productFromDb.getExternalId(), productFromDb);
            Review review = reviewMapper.mapToReview(reviewDto);
            review.setUser(userFromDb);
            review.setProduct(productFromDb);
            reviews.add(review);
        }
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
