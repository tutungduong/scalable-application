package com.javaguides.scalable.dto;


import com.javaguides.scalable.entity.constants.GenderStatus;


import com.javaguides.scalable.entity.constants.RoleStatus;
import com.javaguides.scalable.validator.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch

public class UserDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private GenderStatus gender;
    @NotNull
    private RoleStatus userRole;
    @NotBlank
    private String birthday;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordRepeated;

}
