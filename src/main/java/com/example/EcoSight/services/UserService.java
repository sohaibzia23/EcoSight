package com.example.EcoSight.services;

import com.example.EcoSight.dto.auth.UserRegistrationDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import com.example.EcoSight.mapping.UserRegistrationMapper;
import com.example.EcoSight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(UserRegistrationDto registrationDto) {
        User user = UserRegistrationMapper.mapUsertoUserRegistrationDto(registrationDto);
        return userRepository.save(user);
    }

    public List<User> getAllContributors() {
        return userRepository.findByRole(UserRole.CONTRIBUTOR);
    }

}