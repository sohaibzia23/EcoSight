package com.example.EcoSight.services;


import com.example.EcoSight.dto.sighting.SightingDto;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.Sighting.SightingStatus;
import com.example.EcoSight.entity.Species;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.exceptions.InvalidDataException;
import com.example.EcoSight.exceptions.UserNotFoundException;
import com.example.EcoSight.fileUpload.AzureBlobStorageService;
import com.example.EcoSight.fileUpload.StorageService;
import com.example.EcoSight.mapping.SightingMapper;
import com.example.EcoSight.repository.SightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SightingService {
    private final SightingRepository sightingRepository;
    private final StorageService storageService;

    @Transactional
    public Sighting addSighting(SightingDto sightingDto, User contributor, Species species) {
        if (sightingDto == null) {
            throw new InvalidDataException("Sighting data cannot be null");
        }
        sightingDto.setSightingTime(LocalDateTime.now());
        sightingDto.setStatus(SightingStatus.PENDING);
        Sighting sighting = SightingMapper.mapToEntity(sightingDto, contributor, species);
        return sightingRepository.save(sighting);
    }

    public SightingDto getSightingById(Integer id) {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sighting not found"));
        return SightingMapper.mapToDto(sighting);
    }

    public List<SightingDto> getAllSightings() {
        List<Sighting> sighting = sightingRepository.findAll();
        return sighting.stream().map(SightingMapper::mapToDto).toList();
    }

    public List<SightingDto> getSightingsByUserId(Integer userId) {
        return sightingRepository.findSightingsByUserId(userId).stream()
                .map(SightingMapper::mapToDto)
                .toList();
    }

    @Transactional
    public void deleteSighting(Integer sightingId) {
        Sighting sighting = validateAndGetSighting(sightingId);

        // Delete associated images if they exist
        if (sighting.getImageUrls() != null && !sighting.getImageUrls().isEmpty()) {
            for (String imageUrl : sighting.getImageUrls()) {
                try {
                    if (storageService instanceof AzureBlobStorageService) {
                        ((AzureBlobStorageService) storageService).deleteFileByUrl(imageUrl);
                    } else {
                        String filename = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
                        storageService.deleteFile(filename);
                    }
                } catch (Exception e) {
                    // Log error but continue with deletion
                    System.err.println("Failed to delete image: " + imageUrl);
                }
            }
        }

        sightingRepository.deleteById(sightingId);
    }

    public Sighting validateAndGetSighting(Integer sightingId){
        if (sightingId == null) {
            throw new InvalidDataException("Sighting ID cannot be null");
        }
        return sightingRepository.findById(sightingId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + sightingId));
    }

    public Sighting updateSightingStatus(Integer sightingId, SightingStatus newStatus) {
        Sighting sighting = validateAndGetSighting(sightingId);
        sighting.setStatus(newStatus);
        return sightingRepository.save(sighting);
    }

}
