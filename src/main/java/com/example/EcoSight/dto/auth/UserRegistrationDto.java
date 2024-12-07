package com.example.EcoSight.dto.auth;

import com.example.EcoSight.entity.User.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
}
