package com.example.EcoSight.services;

import com.example.EcoSight.entity.Species;
import com.example.EcoSight.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public Species addSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(String scientificName) {
        speciesRepository.deleteById(scientificName);
    }
}
