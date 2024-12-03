package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Sighting.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
}
