package com.example.EcoSight.services;

import com.example.EcoSight.entity.location.Location;
import com.example.EcoSight.entity.location.LocationId;
import com.example.EcoSight.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public void getOrCreateLocation(Double latitude, Double longitude) {
        LocationId id = new LocationId(latitude, longitude);
        locationRepository.findById(id)
                .orElseGet(() -> {
                    Location newLocation = new Location(id);
                    return locationRepository.save(newLocation);
                });
    }
}
