package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, String> {
    void delete(String scientificName);

    Optional<Species> findBySightingId(Integer sightingID);
}
