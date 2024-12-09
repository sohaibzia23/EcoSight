package com.example.EcoSight.controllers;


import com.example.EcoSight.dto.sighting.SightingDto;
import com.example.EcoSight.dto.sighting.SightingSubmissionDto;
import com.example.EcoSight.dto.sighting.StatusUpdateDto;
import com.example.EcoSight.entity.Sighting.Sighting;
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
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    private final WeatherConditionService weatherConditionService;
    private final ConservationStatusService conservationStatusService;

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

            //Create weather if it doesn't exist
            weatherConditionService.getOrCreateBehaviour(
                    sightingSubmissionDto.getTemperature(),
                    sightingSubmissionDto.getWeatherType()
            );

            //Create conservation status if it doesn't exist
            conservationStatusService.getOrCreateConservationStatus(
                    sightingSubmissionDto.getConservationType(),
                    sightingSubmissionDto.getConservationDescription()
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
            @RequestBody StatusUpdateDto statusUpdate
    ) {
        try {
            User requestUser = userService.validateAndGetUser(requestingUserId);

            if (requestUser.getRole() != UserRole.RESEARCHER) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Sighting updatedSighting = sightingService.updateSightingStatus(sightingId, statusUpdate.getStatus());
            return ResponseEntity.ok(SightingMapper.mapToDto(updatedSighting));

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportSightingsToCsv(
            @RequestHeader("X-User-Id") Integer requestingUserId
    ) {
        try {
            User requestUser = userService.validateAndGetUser(requestingUserId);
            List<SightingDto> sightings;

            if (requestUser.getRole() == UserRole.CONTRIBUTOR) {
                sightings = sightingService.getSightingsByUserId(requestUser.getId());
            } else {
                sightings = sightingService.getAllSightings();
            }

            // Generate CSV content
            StringBuilder csvContent = new StringBuilder();
            // Add CSV header
            csvContent.append("Sighting ID,Date,Time,Species Scientific Name,Species Common Name,Latitude,Longitude,")
                    .append("Behavior,Behavior Level,Temperature,Weather Type,Status\n");

            // Add data rows
            for (SightingDto sighting : sightings) {
                csvContent.append(String.format("%d,%s,%s,%s,%s,%.6f,%.6f,%s,%s,%.1f,%s,%s\n",
                        sighting.getSightingId(),
                        sighting.getSightingTime().toLocalDate(),
                        sighting.getSightingTime().toLocalTime(),
                        sighting.getScientificName(),
                        sighting.getCommonName(),
                        sighting.getLatitude(),
                        sighting.getLongitude(),
                        sighting.getBehaviourName(),
                        sighting.getBehaviourLevelOfActivity(),
                        sighting.getTemperature(),
                        sighting.getWeatherType(),
                        sighting.getStatus()
                ));
            }

            // Convert to bytes and create response
            byte[] csvBytes = csvContent.toString().getBytes(StandardCharsets.UTF_8);

            // Set up response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("sightings_export.csv")
                    .build());

            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
