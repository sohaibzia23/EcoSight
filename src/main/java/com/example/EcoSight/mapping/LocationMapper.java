package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.location.Location;
import com.example.EcoSight.entity.location.LocationId;

public class LocationMapper {

    public static LocationDto toDto(Location location) {
        if (location == null) {
            return null;
        }

        return new LocationDto(
                location.getLocationId().getLatitude(),
                location.getLocationId().getLongitude()
        );
    }

    public static Location toEntity(LocationDto dto) {
        if (dto == null) {
            return null;
        }

        Location location = new Location();
        location.setLocationId(
                new LocationId(
                        dto.getLatitude(),
                        dto.getLongitude()
                )
        );

        return location;
    }
}
