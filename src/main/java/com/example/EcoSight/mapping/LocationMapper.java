package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.Location.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDto toDTO(Location location) {
        return new LocationDto(
                location.getLatLong().getLatitude(),
                location.getLatLong().getLongitude(),
                location.getSighting().getSightingId()
        );
    }
}
