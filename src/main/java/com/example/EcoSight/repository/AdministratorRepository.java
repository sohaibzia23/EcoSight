package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findById(Integer adminID);

    List<Administrator> findByFirstName(String aFirstName);

    Optional<Administrator> findByAdminEmail(String aEmail);


    Optional<Administrator> deleteBySightingID(Integer sightingID);





    
}
