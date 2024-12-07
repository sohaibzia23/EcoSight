package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.SightingDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.services.SightingService;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sightings")
@RequiredArgsConstructor
public class SightingController {

    private final SightingService sightingService;
    private UserService userService;
    @PostMapping
    public ResponseEntity<SightingDto> addSighting(@RequestBody SightingDto sightingDto) {
       try {
           System.out.print("REQUEST RECEIVED\n");
           SightingDto createdSighting = sightingService.addSighting(sightingDto);
           return ResponseEntity.ok(createdSighting);
       }
       catch (Exception e) {
           System.out.print(e.getMessage());
           return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SightingDto>> getSightingsByUserId(@PathVariable Integer userId) {
        try {
            List<SightingDto> sightings = sightingService.getSightingsByUserId(userId);
            return ResponseEntity.ok(sightings);
        }catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SightingDto> getSightingById(@PathVariable Integer id) {
        SightingDto sighting = sightingService.getSightingById(id);
        return ResponseEntity.ok(sighting);
    }




}
