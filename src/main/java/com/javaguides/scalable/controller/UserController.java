package com.javaguides.scalable.controller;


import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.User;
import com.javaguides.scalable.entity.constants.EditMode;
import com.javaguides.scalable.entity.constants.GenderStatus;
import com.javaguides.scalable.entity.constants.RoleStatus;
import com.javaguides.scalable.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // handler method to handle error 5xx
    @GetMapping("/ex")
    public String throwException() {
        throw new RuntimeException("This is a fake exception for testing");
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
    @Secured("ROLE_ADMIN")
    public String createUserForm(Model model) { //<.>
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER)); //<.>
        model.addAttribute("editMode", EditMode.CREATE);
        model.addAttribute("roles",List.of(RoleStatus.values()));
        return "users/edit";
    }
    // handler method to handle user registration form submit request
    @PostMapping("/create")
    @Secured("ROLE_ADMIN")
    public String doCreateUserForm(@Valid @ModelAttribute("user") UserDto userDto,
                                   BindingResult result,
                                   Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        // Fix email ( chua fix )
        if(existingUser !=null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already a user with the given email address.");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER));
            model.addAttribute("editMode", EditMode.CREATE);
            model.addAttribute("roles",List.of(RoleStatus.values()));
            return "users/edit";
        }
        userService.saveUser(userDto);
        return "redirect:/users?success";
    }
    // handler method to handle edit user request
    @GetMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    private String editUser(@PathVariable("userId") Long userId,
                            Model model){
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("user", userDto);
        model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER)); //<.>
        model.addAttribute("editMode", EditMode.UPDATE);
        model.addAttribute("roles",List.of(RoleStatus.values()));

        return "users/edit";
    }
    // handler method to handle edit user form submit request
    @PostMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    private String doEditUser(@PathVariable("userId") Long userId,
                              @ModelAttribute("users") UserDto userDto,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()) {
            model.addAttribute("genders", List.of(GenderStatus.MALE, GenderStatus.FEMALE, GenderStatus.OTHER));
            model.addAttribute("editMode", EditMode.UPDATE);
            model.addAttribute("roles",List.of(RoleStatus.values()));
            return "users/edit";
        }
        userDto.setId(userId);
        userService.updateUser(userDto);
        return "redirect:/users";
    }
    // handler method to handle delete user request
    @GetMapping("/{userId}/delete")
    @Secured("ROLE_ADMIN")
    public String doDeleteUser(@PathVariable("userId") Long userId,
                               RedirectAttributes redirectAttributes) { //<.>
        UserDto user = userService.getUserById(userId);
        userService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("deletedUserName",
                user.getFirstName()+' '+ user.getLastName()); //<.>

        return "redirect:/users";
    }
}