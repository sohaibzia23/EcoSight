package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.entity.User.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }

    public static ContributorDto toContributorDto(Contributor contributor) {
        if (contributor == null) {
            return null;
        }
        ContributorDto dto = new ContributorDto();
        dto.setId(contributor.getId());
        dto.setEmail(contributor.getEmail());
        dto.setFirstName(contributor.getFirstName());
        dto.setLastName(contributor.getLastName());
        dto.setUsername(contributor.getUsername());
        dto.setRole(contributor.getRole());
        return dto;
    }

    public static ResearcherDto toResearcherDto(Researcher researcher) {
        if (researcher == null) {
            return null;
        }
        ResearcherDto dto = new ResearcherDto();
        dto.setId(researcher.getId());
        dto.setEmail(researcher.getEmail());
        dto.setFirstName(researcher.getFirstName());
        dto.setLastName(researcher.getLastName());
        dto.setUsername(researcher.getUsername());
        dto.setRole(researcher.getRole());
        return dto;
    }
}
