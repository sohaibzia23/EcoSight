package com.example.EcoSight.dto;

import com.example.EcoSight.entity.User.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;

}





