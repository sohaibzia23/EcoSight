package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.entity.User.Contributor;
import com.example.EcoSight.entity.User.Researcher;
import com.example.EcoSight.entity.User.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole());
        return dto;
    }

    public static ContributorDto mapToContributorDto(Contributor contributor) {
        if (contributor == null) {
            return null;
        }
        ContributorDto dto = new ContributorDto();
        dto.setId(contributor.getId());
        dto.setEmail(contributor.getEmail());
        dto.setFirstName(contributor.getFirstName());
        dto.setLastName(contributor.getLastName());
        dto.setRole(contributor.getRole());
        return dto;
    }

    public static ResearcherDto mapToResearcherDto(Researcher researcher) {
        if (researcher == null) {
            return null;
        }
        ResearcherDto dto = new ResearcherDto();
        dto.setId(researcher.getId());
        dto.setEmail(researcher.getEmail());
        dto.setFirstName(researcher.getFirstName());
        dto.setLastName(researcher.getLastName());
        dto.setRole(researcher.getRole());
        return dto;
    }

    public static User mapToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user;
        switch (userDto.getRole()) {
            case CONTRIBUTOR:
                user = new Contributor();
                break;
            case RESEARCHER:
                user = new Researcher();
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + userDto.getRole());
        }

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public static Contributor mapToContributor(ContributorDto contributorDto) {
        if (contributorDto == null) {
            return null;
        }
        Contributor contributor = new Contributor();
        contributor.setId(contributorDto.getId());
        contributor.setEmail(contributorDto.getEmail());
        contributor.setFirstName(contributorDto.getFirstName());
        contributor.setLastName(contributorDto.getLastName());
        return contributor;
    }

    public static Researcher mapToResearcher(ResearcherDto researcherDto) {
        if (researcherDto == null) {
            return null;
        }
        Researcher researcher = new Researcher();
        researcher.setId(researcherDto.getId());
        researcher.setEmail(researcherDto.getEmail());
        researcher.setFirstName(researcherDto.getFirstName());
        researcher.setLastName(researcherDto.getLastName());
        return researcher;
    }
}
