package com.example.EcoSight.controllers;


import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.services.SightingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sightings")
@RequiredArgsConstructor
public class SightingController {


    private final SightingService sightingService;

    @PostMapping
    public ResponseEntity<Sighting> addSighting(@RequestBody Sighting sighting) {
       try {
           Sighting createdSighting = sightingService.addSighting(sighting);
           return new ResponseEntity<>(createdSighting, HttpStatus.CREATED);
       }
       catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{sightingId}")
    public ResponseEntity<Void> deleteSighting(@PathVariable Integer sightingId) {
        try {
            sightingService.deleteSighting(sightingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
