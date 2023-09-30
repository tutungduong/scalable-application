package com.javaguides.scalable.service.impl;

import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.Role;
import com.javaguides.scalable.entity.User;
import com.javaguides.scalable.entity.constants.RoleStatus;
import com.javaguides.scalable.mapper.UserMapper;
import com.javaguides.scalable.repository.RoleRepository;
import com.javaguides.scalable.repository.UserRepository;
import com.javaguides.scalable.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setPhoneNumber(userDto.getPhoneNumber());
//        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = checkRoleExist(userDto);
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private Role checkRoleExist(UserDto userDto){
        Role role = new Role();
        role.setName(userDto.getUserRole());
        return roleRepository.save(role);
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findAllUsers(Pageable pageable) {
      Page<User> users = userRepository.findAll(pageable);
        return users.map(UserMapper::mapToUserDto);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        UserDto userDto = UserMapper.mapToUserDto(user);
        return userDto;
    }

    @Override
    public void updateUser(UserDto userDto) {
//        saveUser(userDto);
        userRepository.save(UserMapper.mapToUser(userDto));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
