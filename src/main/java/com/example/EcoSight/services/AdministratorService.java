package com.example.EcoSight.services;

import com.example.EcoSight.administrator.Administrator;
import com.example.EcoSight.administrator.AdministratorRepository;
import com.example.EcoSight.sighting.SightingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final SightingRepository sightingRepository;

    @Autowired
   public AdministratorService(AdministratorRepository administratorRepository, SightingRepository sightingRepository){
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
