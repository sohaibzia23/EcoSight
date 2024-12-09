package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.dto.WeatherConditionDto;
import com.example.EcoSight.entity.ConservationStatus.ConservationType;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.behaviour.LevelOfActivity;
import com.example.EcoSight.entity.weatherCondition.WeatherType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SightingSubmissionDto {
    private String speciesScientificName;
    private String speciesCommonName;
    private Double latitude;
    private Double longitude;
    private String behaviourName;
    private LevelOfActivity behaviourLevelOfActivity;
    private Double temperature;
    private WeatherType weatherType;
    private ConservationType conservationType;
    private String conservationDescription;

    @JsonIgnore // Ignore this field during JSON serialization
    private List<MultipartFile> images;

    public static SightingDto toSightingDto(SightingSubmissionDto submissionDto, User user, List<String> imageUrls){
        if(submissionDto == null){
            return null;
        }
        SightingDto output = new SightingDto();

        output.setScientificName(submissionDto.getSpeciesScientificName());
        output.setContributorId(user.getId());
        output.setContributorEmail(user.getEmail());
        output.setContributorFirstName(user.getFirstName());
        output.setContributorLastName(user.getLastName());
        LocationDto location = new LocationDto(
                submissionDto.getLatitude(),
                submissionDto.getLongitude()
        );
        output.setLocation(location);
        output.setBehaviourName(submissionDto.getBehaviourName());
        output.setBehaviourLevelOfActivity(submissionDto.getBehaviourLevelOfActivity());
        output.setImageUrls(imageUrls);
        output.setSightingTime(LocalDateTime.now());
        output.setWeather(
                new WeatherConditionDto(
                        submissionDto.getTemperature(),
                        submissionDto.getWeatherType()
                )
        );

        output.setConservationType(submissionDto.getConservationType());
        output.setConservationDescription(submissionDto.getConservationDescription());


        return output;
    }
}
