package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, String> {
    Optional<Species> findSpeciesByScientificName(String scientificName);
}
