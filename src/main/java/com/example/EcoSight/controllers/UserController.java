package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.dto.SightingDto;
import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import com.example.EcoSight.services.SightingService;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final SightingService sightingService;


    @GetMapping("/contributors/{id}")
    public ResponseEntity<ContributorDto> getContributorById(@PathVariable Integer id) {
        ContributorDto contributorDto = userService.getContributorById(id);
        return ResponseEntity.ok(contributorDto);
    }

    @GetMapping("/researchers/{id}")
    public ResponseEntity<ResearcherDto> getResearcherById(@PathVariable Integer id) {
        ResearcherDto researcherDto = userService.getResearcherById(id);
        return ResponseEntity.ok(researcherDto);
    }

    @GetMapping("/contributors")
    public ResponseEntity<List<ContributorDto>> getAllContributors() {
        List <ContributorDto> allContributorsDto = userService.getAllContributors();
        return ResponseEntity.ok(allContributorsDto);
    }

    @GetMapping("/researchers")
    public ResponseEntity<List<ResearcherDto>> getAllResearchers() {
        List<ResearcherDto> allResearchersDto = userService.getAllResearchers();
        return ResponseEntity.ok(allResearchersDto);
    }

    @GetMapping("/{contributorId}/sightings")
    public ResponseEntity<List<SightingDto>> getContributorSightings(@PathVariable Integer contributorId) {
        try {
            List<SightingDto> sightings = userService.getSightingsByContributorId(contributorId);
            return ResponseEntity.ok(sightings);
        } catch (RuntimeException e) {
            System.out.print(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{administratorId}/contributors/{contributorId}")
    public ResponseEntity<Void> deleteContributorByIdAndSightings(@PathVariable Integer administratorId, @PathVariable Integer contributorId) {
        try {

            UserDto admin = userService.getUserById(administratorId);


            if (!admin.getRole().equals(UserRole.ADMINISTRATOR)) {
                return ResponseEntity.status(403).build();
            }


            ContributorDto contributor = userService.getContributorById(contributorId);


            sightingService.deleteSightingsByContributorId(contributorId);


            userService.deleteContributorById(contributorId);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error deleting contributor and their sightings: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }



}
