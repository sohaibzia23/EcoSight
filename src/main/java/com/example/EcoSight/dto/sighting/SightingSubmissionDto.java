package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.entity.User.User;
import lombok.Data;

@Data
public class SightingSubmissionDto {
    private String speciesScientificName;
    private String speciesCommonName;
    private String latitude;
    private String longitude;

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
        return output;
    }
}
