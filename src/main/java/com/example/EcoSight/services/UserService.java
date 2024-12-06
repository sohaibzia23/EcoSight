package com.example.EcoSight.services;

import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.entity.User.Contributor;
import com.example.EcoSight.entity.User.Researcher;
import com.example.EcoSight.mapping.UserMapper;
import com.example.EcoSight.repository.ContributorRepository;
import com.example.EcoSight.repository.ResearcherRepository;
import com.example.EcoSight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContributorRepository contributorRepository;
    private final ResearcherRepository researcherRepository;

    public ContributorDto getContributorById(Integer id) {

        Contributor contributor = contributorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contributor not found with id: " + id));

        return UserMapper.mapToContributorDto(contributor);
    }


    public ResearcherDto getResearcherById(Integer id) {

        Researcher researcher = researcherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Researcher not found with id: " + id));
        return UserMapper.mapToResearcherDto(researcher);
    }

    public List<ContributorDto> getAllContributors() {
        return contributorRepository.findAll().stream()
                .map(UserMapper::mapToContributorDto)
                .collect(Collectors.toList());
    }

    public List<ResearcherDto> getAllResearchers() {
        return researcherRepository.findAll().stream()
                .map(UserMapper::mapToResearcherDto)
                .collect(Collectors.toList());
    }


}