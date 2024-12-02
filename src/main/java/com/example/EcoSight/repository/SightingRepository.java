package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    void delete(Integer sightingID);

    List<Sighting> findByUserID(Integer userID);

    List<Sighting> findByValidatedSightings(String validity);
}
