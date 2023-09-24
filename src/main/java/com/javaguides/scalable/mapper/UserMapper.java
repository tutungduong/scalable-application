package com.javaguides.scalable.mapper;

import com.javaguides.scalable.dto.UserDto;
import com.javaguides.scalable.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
//        UserDto userDto = new UserDto();
//        String[] str = user.getUsername().split(" ");
//        userDto.setId(user.getId());
//        userDto.setFirstName(convertToTitleCase(str[0]));
//        userDto.setLastName(convertToTitleCase(str[1]));
//        userDto.setGender(user.getGender());
//        userDto.setBirthday(user.getBirthday());
//        userDto.setEmail(user.getEmail());
//        userDto.setPhoneNumber(user.getPhoneNumber());
        String[] str = user.getUsername().split(" ");
        UserDto userDto = new UserDto(
                user.getId(),
                convertToTitleCase(str[0]),
                convertToTitleCase(str[1]),
                user.getGender(),
                user.getBirthday(),
                user.getEmail(),
                user.getPhoneNumber()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName()+ ' ' + userDto.getLastName(),
                userDto.getGender(),
                userDto.getBirthday(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
        return user;
    }

    // convert String to Title Case
    public static String convertToTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                titleCase.append(Character.toTitleCase(c));
                nextTitleCase = false;
            } else {
                titleCase.append(Character.toLowerCase(c));
            }
        }

        return titleCase.toString();
    }
}