package com.example.EcoSight.services;


import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import com.example.EcoSight.mapping.LocationMapper;
import com.example.EcoSight.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public List<LocationDto> getLocationsBySightingIds(List<Integer> sightingIds) {
        List<Location> locations = locationRepository.findLocationsBySightingIds(sightingIds);
        return locations.stream()
                .map(locationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(LatLong latLong) {
        locationRepository.deleteById(latLong);
    }
}
