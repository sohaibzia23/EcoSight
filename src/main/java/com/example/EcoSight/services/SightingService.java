package com.example.EcoSight.services;


import com.example.EcoSight.entity.Sighting;
import com.example.EcoSight.entity.Species;
import com.example.EcoSight.repository.SightingRepository;
import com.example.EcoSight.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class SightingService {

  private final SightingRepository sightingRepository;


  @Autowired
    public SightingService(SightingRepository sightingRepository) {
      this.sightingRepository = sightingRepository;

  }

  public Optional<Sighting> getSightingById(Integer sightingID){
      return sightingRepository.findById(sightingID);
  }


  public List<Sighting> getAllSightings() {
      return sightingRepository.findAll();
  }


  public Sighting addSighting(Sighting sighting) {
      return sightingRepository.save(sighting);
  }


  public void deleteSightingBySightingId(Integer sightingID) {

      sightingRepository.deleteById(sightingID);
  }

public List<Sighting> getSightingByUserId(Integer userID) {
      return sightingRepository.findByUserID(userID);
}



}
