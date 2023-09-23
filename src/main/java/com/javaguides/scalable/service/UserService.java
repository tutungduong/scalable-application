package com.javaguides.scalable.service;

import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
    Page<UserDto> findAllUsers(Pageable pageable);
    UserDto getUserById(Long userId);

    void updateUser(UserDto userDto);

    void deleteUser(Long userId);
}
