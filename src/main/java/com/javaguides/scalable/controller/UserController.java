package com.javaguides.scalable.controller;


import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.User;
import com.javaguides.scalable.entity.constants.GenderStatus;
import com.javaguides.scalable.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String index(Model model,
                        Pageable pageable) {
        Page<UserDto> users = userService.findAllUsers(pageable);
        model.addAttribute("users",users);
        return "users/list";
    }
    // handler method to handle user registration form request
    @GetMapping("/create") //<.>
    public String createUserForm(Model model) { //<.>
        UserDto user = new UserDto();
        model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER)); //<.>
        model.addAttribute("user", user);
        return "users/edit";
    }
    // handler method to handle user registration form submit request
    @PostMapping("/create")
    public String doCreateUserForm(@Valid @ModelAttribute("user") UserDto userDto,
                                   BindingResult result,
                                   Model model){
        User existingUser =userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER));
            return "users/edit";
        }
        userService.saveUser(userDto);
        return "redirect:/users";
    }
}