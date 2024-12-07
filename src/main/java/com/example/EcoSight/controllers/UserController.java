package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.dto.UserLoginDto;
import com.example.EcoSight.dto.auth.UserRegistrationDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.mapping.UserMapper;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try{
            User createdRegistration = userService.registerUser(registrationDto);
            return ResponseEntity.ok(createdRegistration);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto loginDto) {
        try{
            User account = userService.loginUser(loginDto);
            return ResponseEntity.ok(account);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/contributors")
    public ResponseEntity<List<UserDto>> getAllContributors() {
        try{
            List<User> allContributors = userService.getAllContributors();
            List<UserDto> responseDto = allContributors.stream()
                    .map(UserMapper::mapToUserDto)
                    .toList();
            return ResponseEntity.ok(responseDto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
