package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, String> {

}
