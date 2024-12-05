package com.example.EcoSight.services;


import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.mapping.LocationMapper;
import com.example.EcoSight.repository.LocationRepository;
import com.example.EcoSight.repository.SightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final SightingRepository sightingRepository;

    @Transactional
    public LocationDto addLocation(LocationDto locationDto) {
        Sighting sighting = sightingRepository.findById(locationDto.getSightingId())
                .orElseThrow(() -> new IllegalArgumentException("Sighting not found with ID: " + locationDto.getSightingId()));

        if (locationRepository.findBySighting_SightingId(locationDto.getSightingId()).isPresent()) {
            throw new IllegalStateException("Location already exists for sighting ID: " + locationDto.getSightingId());
        }

        Location newLocation = new Location();
        LatLong latLong = new LatLong(locationDto.getLatitude(), locationDto.getLongitude());
        newLocation.setLatLong(latLong);
        newLocation.setSighting(sighting);

        Location savedLocation = locationRepository.save(newLocation);
        return locationMapper.toDTO(savedLocation);
    }

    public List<LocationDto> getLocationsBySightingIds(List<Integer> sightingIds) {
        List<Location> locations = locationRepository.findLocationsBySightingIds(sightingIds);
        return locations.stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLocation(LatLong latLong) {
        Location location = locationRepository.findById(latLong)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        locationRepository.delete(location);
    }
}
