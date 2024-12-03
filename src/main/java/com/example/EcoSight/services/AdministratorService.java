package com.example.EcoSight.services;

import com.example.EcoSight.entity.Administrator;
import com.example.EcoSight.repository.AdministratorRepository;
import com.example.EcoSight.repository.ResearcherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final ResearcherRepository.SightingRepository sightingRepository;

    @Autowired
   public AdministratorService(AdministratorRepository administratorRepository, ResearcherRepository.SightingRepository sightingRepository){
        this.administratorRepository = administratorRepository;
        this.sightingRepository = sightingRepository;
    }

    public Optional<Administrator> getAdminById(Integer adminID) {

        return administratorRepository.findById(adminID);
    }

    public Optional<Administrator> getAdministratorByEmail(String email) {
        return administratorRepository.findByAdminEmail(email);
    }

    public void deleteBySightingId(Integer sightingID) {

        sightingRepository.deleteBySightingID(sightingID);
    }

    public List<Administrator> findByAdminFirstName(String firstName) {
        return administratorRepository.findByFirstName(firstName);
    }




}
