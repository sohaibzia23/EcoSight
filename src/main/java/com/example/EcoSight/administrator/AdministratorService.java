package com.example.EcoSight.administrator;

import com.example.EcoSight.sighting.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
