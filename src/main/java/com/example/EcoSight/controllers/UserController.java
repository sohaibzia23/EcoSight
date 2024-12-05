package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

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

}
