package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.dto.WeatherConditionDto;
import com.example.EcoSight.entity.ConservationStatus.ConservationType;
import com.example.EcoSight.entity.Sighting.SightingStatus;
import com.example.EcoSight.entity.behaviour.LevelOfActivity;
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
    private String behaviourName;
    private LevelOfActivity behaviourLevelOfActivity;
    private WeatherConditionDto weather;
    private ConservationType conservationType;
    private String conservationDescription;

}