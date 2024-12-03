package com.example.EcoSight.services;


import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.repository.SightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SightingService {

    private final SightingRepository sightingRepository;

    public Sighting addSighting(Sighting sighting) {
        return sightingRepository.save(sighting);
    }

    public void deleteSighting(Integer sightingId) {
        sightingRepository.deleteById(sightingId);
    }
}
