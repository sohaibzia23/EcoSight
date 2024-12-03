package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
