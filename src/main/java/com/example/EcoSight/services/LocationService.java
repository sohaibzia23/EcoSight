package com.example.EcoSight.services;


import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import com.example.EcoSight.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(LatLong latLong) {
        locationRepository.deleteById(latLong);
    }

    public Optional<Location> getLocationById(LatLong latLong) {
        return locationRepository.findById(latLong);
    }

    public List<Location> getLocationBySightingId(Integer sightingId) {
        return locationRepository.findBySighting_SightingID(sightingId);
    }

}
