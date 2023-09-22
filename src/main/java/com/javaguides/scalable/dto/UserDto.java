package com.javaguides.scalable.dto;


import com.javaguides.scalable.entity.constants.GenderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderStatus gender;
    private String birthday;
    private String email;
    private String phone;
}
