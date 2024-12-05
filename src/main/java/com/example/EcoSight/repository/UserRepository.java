package com.example.EcoSight.repository;

import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findContributorsByRole(UserRole role);

    List<User> findResearchersByRole(UserRole role);
}
