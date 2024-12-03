package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component

public interface SpeciesRepository extends JpaRepository<Species, String> {
    void delete(String scientificName);

    Optional<Species> findBySightingId(Integer sightingID);
}
