package com.example.EcoSight.services;

import com.example.EcoSight.contributor.Contributor;
import com.example.EcoSight.contributor.ContributorRepository;
import com.example.EcoSight.repository.SightingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ContributorService {

    private final ContributorRepository contributorRepository;
    private final SightingRepository sightingRepository;

    @Autowired
    public ContributorService(ContributorRepository contributorRepository, SightingRepository sightingRepository) {
        this.contributorRepository = contributorRepository;
        this.sightingRepository = sightingRepository;

    }

    public Optional<Contributor> getContributorById(Integer contributorID) {
        return contributorRepository.findByContributorID(contributorID);
    }

   public List<Contributor> getAllContributors() {
        return contributorRepository.findAll();
    }


    public Contributor addContributor(Contributor contributor) {
        return contributorRepository.save(contributor);
    }

    public void deleteBySightingId(Integer sightingID) {
        sightingRepository.delete(sightingID);
    }





}
