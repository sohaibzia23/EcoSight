package com.example.EcoSight.services;

import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.repository.ResearcherRepository;

import java.util.Optional;

public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    public ResearcherService(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

    public Optional<Researcher> getResearcherById(Integer researcherID) {
       return researcherRepository.findById(researcherID);
    }

    public Optional<Researcher> getResearcherByEmail(String researcherEmail) {
        return researcherRepository.findByResearcherEmail(researcherEmail);
    }



}
