package com.example.EcoSight.entity.researcher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResearcherRepository extends JpaRepository<Researcher, Integer> {


    Optional<Researcher> findByID(Integer researcherID);


}
