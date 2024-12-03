package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.entity.Sighting.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Integer> {

    Optional<Researcher> findById(Integer researcherID);

    List<Researcher> findByFirstName(String researcherFirstName);

    Optional<Researcher> findByResearcherEmail(String researcherEmail);


    interface SightingRepository extends JpaRepository<Sighting, String> {

        Optional<Sighting> findByID(Integer sightingID);


        Optional<Sighting> deleteBySightingID(Integer sightingID);


        List<Sighting> findAll();

        List<Sighting> findAllValidSightings(Boolean validity);

        List<Sighting> findByUserID(Integer userID);

    }
}
