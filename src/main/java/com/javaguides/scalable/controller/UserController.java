package com.javaguides.scalable.controller;


import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle list of users
    @GetMapping
    public String index(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users/list";
    }
}