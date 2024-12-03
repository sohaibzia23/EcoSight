package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LatLong> {
}
