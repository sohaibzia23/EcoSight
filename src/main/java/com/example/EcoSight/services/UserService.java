package com.example.EcoSight.services;

import com.example.EcoSight.dto.ContributorDto;
import com.example.EcoSight.dto.ResearcherDto;
import com.example.EcoSight.dto.SightingDto;
import com.example.EcoSight.dto.UserDto;
import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.mapping.SightingMapper;
import com.example.EcoSight.mapping.UserMapper;
import com.example.EcoSight.repository.ContributorRepository;
import com.example.EcoSight.repository.ResearcherRepository;
import com.example.EcoSight.repository.SightingRepository;
import com.example.EcoSight.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContributorRepository contributorRepository;
    private final ResearcherRepository researcherRepository;
    private final SightingRepository sightingRepository;
    private final SightingMapper sightingMapper;



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


    public List<SightingDto> getSightingsByContributorId(Integer contributorId) {
        Contributor contributor = contributorRepository.findById(contributorId)
                .orElseThrow(() -> new RuntimeException("Contributor not found with id: " + contributorId));

                return sightingRepository.findSightingsByUserId(contributorId).stream()
                        .map(sightingMapper::toDto)
                        .collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public void deleteContributorById(Integer contributorId) {
        Contributor contributor = contributorRepository.findById(contributorId)
                .orElseThrow(() -> new RuntimeException("Contributor not found with id: " + contributorId));

        contributorRepository.delete(contributor);
    }


}