package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.dto.UserLoginDto;
import com.example.EcoSight.dto.auth.UserRegistrationDto;
import com.example.EcoSight.dto.sighting.SightingDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import com.example.EcoSight.mapping.UserMapper;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestHeader("X-User-Id") Integer requestingUserId
    ) {
        try{
            User requestUser = userService.validateAndGetUser(requestingUserId);
            if (requestUser.getRole() != UserRole.ADMINISTRATOR){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            List<User> allContributors = userService.getAllUsers();
            List<UserDto> responseDto = allContributors.stream()
                    .filter(user -> user.getRole() != UserRole.ADMINISTRATOR)
                    .map(UserMapper::mapToUserDto)
                    .toList();
            return ResponseEntity.ok(responseDto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByUserId(
            @PathVariable Integer id,
            @RequestHeader("X-User-Id") Integer requestingUserId
            ) {
        try{
            User requestUser = userService.validateAndGetUser(requestingUserId);
            if (requestUser.getRole() != UserRole.ADMINISTRATOR){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            User user = userService.getUser(id);
            UserDto dto= UserMapper.mapToUserDto(user);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
