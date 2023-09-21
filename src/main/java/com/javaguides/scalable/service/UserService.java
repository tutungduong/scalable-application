package com.javaguides.scalable.service;

import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
