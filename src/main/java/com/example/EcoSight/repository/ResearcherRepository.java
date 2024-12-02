package com.example.EcoSight.repository;

import com.example.EcoSight.administrator.Administrator;
import com.example.EcoSight.entity.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResearcherRepository extends JpaRepository<Researcher, Integer> {

    Optional<Researcher> findById(Integer researcherID);

    List<Researcher> findByFirstName(String researcherFirstName);

    Optional<Researcher> findByResearcherEmail(String researcherEmail);


}
