package com.example.EcoSight.controllers;

import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.services.AdministratorService;
import com.example.EcoSight.services.ContributorService;
import com.example.EcoSight.services.SightingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/{adminID}")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final SightingService sightingService;

    private final ContributorService contributorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService, SightingService sightingService, ContributorService contributorService) {
        this.administratorService = administratorService;
        this.sightingService = sightingService;
        this.contributorService = contributorService;
    }

    @GetMapping("/sightings/{sightingID}")
    public List<Sighting> getSightings(@RequestParam(required = false) Integer sightingID) {
        if (sightingID != null) {
            Optional<Sighting> sighting = sightingService.getSightingById(sightingID);
            return sighting.stream().toList();
        } else {
            return sightingService.getAllSightings();
        }
    }

    @DeleteMapping("/sightings/{sightingID}")
    public ResponseEntity<String> deleteSightingById(@PathVariable Integer sightingID) {
        Optional<Sighting> sighting = sightingService.getSightingById(sightingID);
        if (sighting.isPresent()) {
            sightingService.deleteSightingBySightingId(sightingID);
            return ResponseEntity.ok("Sighting " + sightingID + " deleted.");
        } else {
            return ResponseEntity.status(404).body("Sighting " + sightingID + " not found.");
        }
    }


    @GetMapping("/sightings/user/{userID}")
    public List<Sighting> getSightingsByUserId(@PathVariable Integer userID) {
        return sightingService.getSightingByUserId(userID);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
        contributorService.deleteByUserId(userId);
        return ResponseEntity.ok("User " + userId + " deleted.");
    }





}
