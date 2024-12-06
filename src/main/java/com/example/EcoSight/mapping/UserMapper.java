package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;

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
        dto.setUsername(user.getUsername());
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
        dto.setUsername(contributor.getUsername());
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
        dto.setUsername(researcher.getUsername());
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
        user.setUsername(userDto.getUsername());
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
        contributor.setUsername(contributorDto.getUsername());
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
        researcher.setUsername(researcherDto.getUsername());
        return researcher;
    }
}
