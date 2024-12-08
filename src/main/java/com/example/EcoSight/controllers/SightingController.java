package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.sighting.SightingDto;
import com.example.EcoSight.dto.sighting.SightingSubmissionDto;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.Sighting.SightingStatus;
import com.example.EcoSight.entity.Species;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import com.example.EcoSight.exceptions.InvalidDataException;
import com.example.EcoSight.exceptions.UserNotFoundException;
import com.example.EcoSight.mapping.SightingMapper;
import com.example.EcoSight.services.LocationService;
import com.example.EcoSight.services.SightingService;
import com.example.EcoSight.services.SpeciesService;
import com.example.EcoSight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sightings")
@RequiredArgsConstructor
public class SightingController {

    private final SightingService sightingService;
    private final UserService userService;
    private final SpeciesService speciesService;
    private final LocationService locationService;

    @PostMapping("/create")
    public ResponseEntity<SightingDto> addSighting(
            @RequestHeader("X-User-Id") Integer requestingUserId,
            @RequestBody SightingSubmissionDto sightingSubmissionDto) {
        try {
            // Get user or throw exception
            User requestUser = userService.validateAndGetUser(requestingUserId);

            // Get or create species
            Species species = speciesService.getOrCreateSpecies(
                    sightingSubmissionDto.getSpeciesScientificName(),
                    sightingSubmissionDto.getSpeciesCommonName()
            );

            //Create location if it doesn't exist
            locationService.getOrCreateLocation(
                    sightingSubmissionDto.getLatitude(),
                    sightingSubmissionDto.getLongitude()
            );

            SightingDto sightingDto = SightingSubmissionDto.toSightingDto(sightingSubmissionDto, requestUser);
            Sighting sighting = sightingService.addSighting(sightingDto, requestUser, species);
            return ResponseEntity.ok(sightingDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SightingDto>> getSightingsByUserId(
            @PathVariable Integer userId,
            @RequestHeader("X-User-Id") Integer requestingUserId
    ){
        try {
            // Get user or throw exception
            User requestUser = userService.validateAndGetUser(requestingUserId);
            userService.validateAndGetUser(userId);

            if(requestUser.getRole() == UserRole.CONTRIBUTOR && requestUser.getId() != userId){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }else{
                List<SightingDto> sightings = sightingService.getSightingsByUserId(userId);
                return ResponseEntity.ok(sightings);
            }
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SightingDto>> getAllSightings(
            @RequestHeader("X-User-Id") Integer requestingUserId
    ){
        try {
            // Get user or throw exception
            User requestUser = userService.validateAndGetUser(requestingUserId);

            if(requestUser.getRole() == UserRole.CONTRIBUTOR){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }else{
                List<SightingDto> sightings = sightingService.getAllSightings();
                return ResponseEntity.ok(sightings);
            }
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SightingDto> getSightingBySightingId(@PathVariable Integer id) {
        try{
            SightingDto sighting = sightingService.getSightingById(id);
            return ResponseEntity.ok(sighting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{sightingId}")
    public ResponseEntity<Void> deleteSighting(
            @PathVariable Integer sightingId,
            @RequestHeader("X-User-Id") Integer requestingUserId
            ) {
        try {
            User requestUser = userService.validateAndGetUser(requestingUserId);
            Sighting deletionCandidate = sightingService.validateAndGetSighting(sightingId);

            if(requestUser.getRole() == UserRole.CONTRIBUTOR && requestUser.getId() == deletionCandidate.getContributor().getId()){
                sightingService.deleteSighting(sightingId);
                // We should probably also delete all other items dependent on it too
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{sightingId}/status")
    public ResponseEntity<SightingDto> updateSightingStatus(
            @PathVariable Integer sightingId,
            @RequestHeader("X-User-Id") Integer requestingUserId,
            @RequestBody SightingStatus newStatus
    ) {
        try {
            User requestUser = userService.validateAndGetUser(requestingUserId);

            if (requestUser.getRole() != UserRole.RESEARCHER) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Sighting updatedSighting = sightingService.updateSightingStatus(sightingId, newStatus);
            return ResponseEntity.ok(SightingMapper.mapToDto(updatedSighting));

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
