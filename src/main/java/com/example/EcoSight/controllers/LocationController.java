package com.example.EcoSight.controllers;



import com.example.EcoSight.entity.Location.LatLong;
import com.example.EcoSight.entity.Location.Location;
import com.example.EcoSight.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        try {
            Location newLocation = locationService.addLocation(location);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLocation(@PathVariable LatLong latLong) {
        try {
            locationService.deleteLocation(latLong);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
