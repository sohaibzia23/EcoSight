package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, LatLong> {


List<Location> findBySighting_SightingID(Integer sightingId);
}
