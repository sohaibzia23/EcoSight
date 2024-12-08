package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.dto.LocationDto;
import com.example.EcoSight.entity.User.User;
import lombok.Data;

@Data
public class SightingSubmissionDto {
    private String speciesScientificName;
    private String speciesCommonName;
    private Double latitude;
    private Double longitude;

    public static SightingDto toSightingDto(SightingSubmissionDto submissionDto, User user){
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

        return output;
    }
}
