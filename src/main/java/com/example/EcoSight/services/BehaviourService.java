package com.example.EcoSight.services;

import com.example.EcoSight.entity.behaviour.Behaviour;
import com.example.EcoSight.entity.behaviour.BehaviourId;
import com.example.EcoSight.entity.behaviour.LevelOfActivity;
import com.example.EcoSight.repository.BehaviourRepository;

public class BehaviourService {
    BehaviourRepository behaviourRepository;

    public void getOrCreateBehaviour(String name, LevelOfActivity levelOfActivity) {
        BehaviourId id = new BehaviourId(name, levelOfActivity);
        behaviourRepository.findById(id)
                .orElseGet(() -> {
                    Behaviour newBehaviour = new Behaviour(id);
                    return behaviourRepository.save(newBehaviour);
                });
    }
}
