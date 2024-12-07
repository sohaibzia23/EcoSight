package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.auth.UserRegistrationDto;
import com.example.EcoSight.entity.User.User;

public class UserRegistrationMapper {

    public static User mapUsertoUserRegistrationDto(UserRegistrationDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        System.out.print(dto.getRole());
        user.setIsActive(true);
        return user;
    }
}
