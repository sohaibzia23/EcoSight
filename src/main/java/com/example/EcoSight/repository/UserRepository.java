package com.example.EcoSight.repository;

import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);

    Optional<User> findByEmailAndPassword(String email, String password);
}
