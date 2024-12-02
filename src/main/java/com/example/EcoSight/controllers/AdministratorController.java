package com.example.EcoSight.controllers;

import com.example.EcoSight.entity.Sighting;
import com.example.EcoSight.services.AdministratorService;
import com.example.EcoSight.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/{adminID}") // Specify a URL mapping for administrator actions
public class AdministratorController {

    private final AdministratorService administratorService;
    private final SightingService sightingService;

    @Autowired
    public AdministratorController(AdministratorService administratorService, SightingService sightingService) {
        this.administratorService = administratorService;
        this.sightingService = sightingService;
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
    public String deleteSightingById(@PathVariable Integer sightingID) {
        sightingService.deleteSightingBySightingId(sightingID);
        return "Sighting " + sightingID + "deleted.";
    }

    @GetMapping("/sightings/user/{userID}")
    public List<Sighting> getSightingsByUserId(@PathVariable Integer userID) {
        return sightingService.getSightingByUserId(userID);
    }

}
