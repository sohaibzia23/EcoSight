package com.example.EcoSight.services;


import com.example.EcoSight.dto.SightingDto;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.Sighting.SightingStatus;
import com.example.EcoSight.mapping.SightingMapper;
import com.example.EcoSight.repository.SightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SightingService {
    private final SightingRepository sightingRepository;
    private final SightingMapper sightingMapper;

    @Transactional
    public SightingDto addSighting(SightingDto sightingDto) {
        sightingDto.setTime(LocalDateTime.now());
        sightingDto.setStatus(SightingStatus.PENDING);
        Sighting sighting = sightingMapper.toEntity(sightingDto);
        Sighting savedSighting = sightingRepository.save(sighting);
        return sightingMapper.toDto(savedSighting);
    }

    public SightingDto getSightingById(Integer id) {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sighting not found"));
        return sightingMapper.toDto(sighting);
    }

    public SightingDto getPendingSightingsByUserId(Integer userId) {
        Sighting sighting = sightingRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Sighting not found"));
        return sightingMapper.toDto(sighting);
    }

    public List<SightingDto> getSightingsByUserId(Integer userId) {
        return sightingRepository.findSightingsByUserId(userId).stream()
                .map(sightingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSighting(Integer sightingId) {
        Sighting sighting = sightingRepository.findById(sightingId)
                .orElseThrow(() -> new RuntimeException("Sighting not found"));
        sightingRepository.delete(sighting);
    }

    @Transactional
    public void deleteSightingsByContributorId(Integer contributorId) {
        List<Sighting> sightings = sightingRepository.findSightingsByUserId(contributorId);
        if (!sightings.isEmpty()) {
            sightingRepository.deleteAll(sightings);
        }
    }

}
