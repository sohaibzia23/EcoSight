package com.example.EcoSight.services;


import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import com.example.EcoSight.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(LatLong latLong) {
        locationRepository.deleteById(latLong);
    }
}
