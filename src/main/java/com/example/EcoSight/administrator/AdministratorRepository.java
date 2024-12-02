package com.example.EcoSight.administrator;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findById(Integer adminID);

    List<Administrator> findByFirstName(String aFirstName);

    Optional<Administrator> findByAdminEmail(String aEmail);








    
}
