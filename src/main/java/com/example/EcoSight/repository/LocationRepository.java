package com.example.EcoSight.repository;

import com.example.EcoSight.entity.location.Location;
import com.example.EcoSight.entity.location.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
