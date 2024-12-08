package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.dto.sighting.SightingDto;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.Species;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.behaviour.Behaviour;
import com.example.EcoSight.entity.behaviour.BehaviourId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SightingMapper {

    public static SightingDto mapToDto(Sighting sighting) {
        if (sighting == null) {
            return null;
        }
        SightingDto dto = new SightingDto();
        dto.setSightingId(sighting.getSightingId());
        dto.setSightingTime(sighting.getSightingTime());

        if (sighting.getContributor() != null) {
            dto.setContributorId(sighting.getContributor().getId());
            dto.setContributorEmail(sighting.getContributor().getEmail());
            dto.setContributorFirstName(sighting.getContributor().getFirstName());
            dto.setContributorLastName(sighting.getContributor().getLastName());
        }

        if (sighting.getSpecies() != null) {
            dto.setScientificName(sighting.getSpecies().getScientificName());
        }

        dto.setImageUrls(sighting.getImageUrls() != null ?
                new ArrayList<>(sighting.getImageUrls()) :
                new ArrayList<>());
        LocationDto locationDto = new LocationDto(
                sighting.getLocation().getLocationId().getLatitude(),
                sighting.getLocation().getLocationId().getLongitude()
        );

        dto.setLocation(locationDto);

        dto.setStatus(sighting.getStatus());
        dto.setBehaviourName(sighting.getBehaviour().getBehaviourId().getName());
        dto.setBehaviourLevelOfActivity(sighting.getBehaviour().getBehaviourId().getLevelOfActivity());


        return dto;
    }

    public static List<SightingDto> mapToDtoList(List<Sighting> sightings) {
        return sightings.stream()
                .map(SightingMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static Sighting mapToEntity(SightingDto dto, User contributor, Species species) {
        if (dto == null) {
            return null;
        }

        Sighting sighting = new Sighting();
        sighting.setSightingId(dto.getSightingId());
        sighting.setSightingTime(dto.getSightingTime());
        sighting.setContributor(contributor);
        sighting.setSpecies(species);
        sighting.setImageUrls(dto.getImageUrls() != null ?
                new ArrayList<>(dto.getImageUrls()) :
                new ArrayList<>());
        sighting.setStatus(dto.getStatus());
        sighting.setLocation(LocationMapper.toEntity(dto.getLocation()));
        sighting.setBehaviour(
                new Behaviour(
                        new BehaviourId(
                                dto.getBehaviourName(),
                                dto.getBehaviourLevelOfActivity()
                        )
                )
        );

        return sighting;
    }

}