package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Sighting.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    @Query("SELECT s FROM Sighting s WHERE s.contributor.id = :userId")
    List<Sighting> findSightingsByUserId(@Param("userId") Integer userId);
}
