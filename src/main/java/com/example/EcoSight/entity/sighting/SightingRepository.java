package com.example.EcoSight.entity.sighting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SightingRepository extends JpaRepository<Sighting, String> {

    Optional<Sighting> findByID(Integer sightingID);


    Optional<Sighting> deleteBySightingID(Integer sightingID);


    List<Sighting> findAll();

    List<Sighting> findAllValidSightings(Boolean validity);

    List<Sighting> findByUserID(Integer userID);

}
