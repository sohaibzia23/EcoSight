package com.example.EcoSight.controllers;



import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto) {
        try {
            System.out.print("REQUEST RECEIVED\n");
            LocationDto newLocation = locationService.addLocation(locationDto);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/by-sightings")
    public ResponseEntity<List<LocationDto>> getLocationsBySightingIds(
            @RequestBody List<Integer> sightingIds
    ) {
        List<LocationDto> locations = locationService.getLocationsBySightingIds(sightingIds);
        return ResponseEntity.ok(locations);
    }

    @DeleteMapping("/{latitude}/{longitude}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable Float latitude,
            @PathVariable Float longitude
    ) {
        try {
            LatLong latLong = new LatLong(latitude, longitude);
            locationService.deleteLocation(latLong);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
