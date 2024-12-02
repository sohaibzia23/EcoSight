package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Sighting.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    void delete(Integer sightingID);

    List<Sighting> findByUserID(Integer userID);

    List<Sighting> findByValidatedSightings(String validity);


}
