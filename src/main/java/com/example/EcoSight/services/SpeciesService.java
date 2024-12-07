package com.example.EcoSight.services;

import com.example.EcoSight.entity.Species;
import com.example.EcoSight.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public Species addSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(String scientificName) {
        speciesRepository.deleteById(scientificName);
    }

    public Species getOrCreateSpecies(String scientificName, String commonName) {
        return speciesRepository.findSpeciesByScientificName(scientificName)
                .orElseGet(() -> {
                    Species newSpecies = new Species(scientificName, commonName);
                    return speciesRepository.save(newSpecies);
                });
    }

}
