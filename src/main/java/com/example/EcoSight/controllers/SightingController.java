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
import com.example.EcoSight.fileUpload.AzureBlobStorageService;
import com.example.EcoSight.fileUpload.StorageFileNotFoundException;
import com.example.EcoSight.fileUpload.StorageService;
import com.example.EcoSight.mapping.SightingMapper;
import com.example.EcoSight.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sightings")
@RequiredArgsConstructor
public class SightingController {

    private final SightingService sightingService;
    private final UserService userService;
    private final SpeciesService speciesService;
    private final LocationService locationService;
    private final BehaviourService behaviourService;
    private final StorageService storageService;

    @PostMapping("/create")
    public ResponseEntity<SightingDto> addSighting(
            @RequestHeader("X-User-Id") Integer requestingUserId,
            @ModelAttribute SightingSubmissionDto sightingSubmissionDto) {
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

            //Create behaviour if it doesn't exist
            behaviourService.getOrCreateBehaviour(
                    sightingSubmissionDto.getBehaviourName(),
                    sightingSubmissionDto.getBehaviourLevelOfActivity()
            );

            // Handle file uploads and get URLs
            List<String> imageUrls = new ArrayList<>();
            if (sightingSubmissionDto.getImages() != null && !sightingSubmissionDto.getImages().isEmpty()) {
                for (MultipartFile file : sightingSubmissionDto.getImages()) {
                    String fileUrl = storageService.store(file);
                    imageUrls.add(fileUrl);
                }
            }

            SightingDto sightingDto = SightingSubmissionDto.toSightingDto(sightingSubmissionDto, requestUser, imageUrls);
            Sighting addedSighting = sightingService.addSighting(sightingDto, requestUser, species);
            sightingDto.setSightingId(addedSighting.getSightingId());
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

            if (requestUser.getRole() == UserRole.CONTRIBUTOR &&
                    requestUser.getId() == deletionCandidate.getContributor().getId()) {

                // Delete associated images if they exist
                if (deletionCandidate.getImageUrls() != null && !deletionCandidate.getImageUrls().isEmpty()) {
                    for (String imageUrl : deletionCandidate.getImageUrls()) {
                        try {
                            if (storageService instanceof AzureBlobStorageService) {
                                ((AzureBlobStorageService) storageService).deleteFileByUrl(imageUrl);
                            } else {
                                  // For local storage, extract filename from URL
//                                String filename = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
//                                storageService.deleteFile(filename);
                            }
                        } catch (StorageFileNotFoundException e) {
                            // Log but continue if file is already gone
                            System.err.println("Image file not found: " + imageUrl);
                        } catch (Exception e) {
                            // Log the error but continue with deletion
                            System.err.println("Failed to delete image: " + imageUrl + ". Error: " + e.getMessage());
                        }
                    }
                }

                // Delete the sighting
                sightingService.deleteSighting(sightingId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            System.err.println("Failed to delete sighting: " + e.getMessage());
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
