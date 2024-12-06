package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.SightingDto;
import com.example.EcoSight.entity.User.Contributor;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SightingMapper {
    private final ContributorRepository contributorRepository;

    public SightingDto toDto(Sighting sighting) {
        return new SightingDto(
                sighting.getSightingId(),
                sighting.getTime(),
                sighting.getContributor().getId(),
                sighting.getStatus()
        );
    }

    public Sighting toEntity(SightingDto sightingDto) {
        Contributor contributor = contributorRepository.findById(sightingDto.getContributorId())
                .orElseThrow(() -> new RuntimeException("Contributor not found"));

        return new Sighting(
                sightingDto.getSightingId(),
                sightingDto.getTime(),
                contributor,
                sightingDto.getStatus()
        );
    }

}
