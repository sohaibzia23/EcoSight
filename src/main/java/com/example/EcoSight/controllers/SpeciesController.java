package com.example.EcoSight.controllers;

import com.example.EcoSight.entity.Species;
import com.example.EcoSight.services.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;

    public ResponseEntity<Species> addSpecies(@RequestBody Species species) {
        try {
            Species newSpecies = speciesService.addSpecies(species);
            return new ResponseEntity<>(newSpecies, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> deleteSpecies(@PathVariable String scientificName) {
        try {
            speciesService.deleteSpecies(scientificName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
