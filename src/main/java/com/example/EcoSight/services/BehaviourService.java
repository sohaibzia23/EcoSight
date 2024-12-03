package com.example.EcoSight.services;


import com.example.EcoSight.entity.Behaviour.Behaviour;
import com.example.EcoSight.repository.BehaviourRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class BehaviourService {

    private final BehaviourRepository behaviourRepository;


    public BehaviourService(BehaviourRepository behaviourRepository) {
        this.behaviourRepository = behaviourRepository;
    }

    public Behaviour addBehaviour(Behaviour behaviour) {
        return behaviourRepository.save(behaviour);
    }

    public Optional<Behaviour> getBehaviourByName(String name) {
        return behaviourRepository.findById(name);
    }

    public void deleteBehaviour(String name) {
        behaviourRepository.deleteById(name);
    }

    public List<Behaviour> getBehaviourBySpecies(String scientificName) {
        return behaviourRepository.findBehaviourBySpecies(scientificName);
    }
}
