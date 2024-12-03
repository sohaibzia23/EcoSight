package com.example.EcoSight.repository;

import com.example.EcoSight.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
