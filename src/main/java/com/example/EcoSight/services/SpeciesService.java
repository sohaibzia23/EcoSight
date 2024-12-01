package com.example.EcoSight.services;


import com.example.EcoSight.entity.Species;
import com.example.EcoSight.repository.SightingRepository;
import com.example.EcoSight.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class SpeciesService {


    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }


    public Species addSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(String scientificName) {
        speciesRepository.delete(scientificName);
    }

    public Optional<Species> getSpeciesByScientificName(String scientificName) {
        return speciesRepository.findById(scientificName);
    }

    public Optional<Species> getSpeciesBySightingId(Integer sightingID) {
        return speciesRepository.findBySightingId(sightingID);
    }
}