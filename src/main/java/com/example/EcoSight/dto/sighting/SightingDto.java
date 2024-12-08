package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.Sighting.SightingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightingDto {
    private Integer sightingId;
    private LocalDateTime sightingTime;
    private Integer contributorId;
    private String contributorEmail;
    private String contributorFirstName;
    private String contributorLastName;
    private String scientificName;
    private LocationDto location;
    private List<String> imageUrls;
    private SightingStatus status;
}