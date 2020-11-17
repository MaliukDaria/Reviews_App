package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    @Query("SELECT new com.example.demo.model.dto.UserResponseDto(u.profileName) "
            + "FROM Review r "
            + "JOIN r.user u "
            + "GROUP BY u.id "
            + "ORDER BY COUNT(r) DESC")
    List<UserResponseDto> getActiveUsers(Pageable pageable);
}
