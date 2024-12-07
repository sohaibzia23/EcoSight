package com.example.EcoSight.services;

import com.example.EcoSight.dto.UserLoginDto;
import com.example.EcoSight.dto.auth.UserRegistrationDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import com.example.EcoSight.exceptions.InvalidDataException;
import com.example.EcoSight.exceptions.UserNotFoundException;
import com.example.EcoSight.mapping.UserRegistrationMapper;
import com.example.EcoSight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(UserRegistrationDto registrationDto) {
        User user = UserRegistrationMapper.mapUsertoUserRegistrationDto(registrationDto);
        return userRepository.save(user);
    }

    public User loginUser(UserLoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException("Invalid email or password");
        }
    }

    public List<User> getAllContributors() {
        return userRepository.findByRole(UserRole.CONTRIBUTOR);
    }

    public User validateAndGetUser(Integer userId){
        if (userId == null) {
            throw new InvalidDataException("User ID cannot be null");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
    }
}