package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, LatLong> {
    @Query("SELECT l FROM Location l WHERE l.sighting.sightingId IN :sightingIds")
    List<Location> findLocationsBySightingIds(@Param("sightingIds") List<Integer> sightingIds);

    Optional<Location> findBySighting_SightingId(Integer sightingId);
}
