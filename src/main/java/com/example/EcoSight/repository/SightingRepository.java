package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Sighting.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    List<Sighting> findSightingsByUserId(Integer userId);
}
