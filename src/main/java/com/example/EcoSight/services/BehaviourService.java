package com.example.EcoSight.services;

import com.example.EcoSight.entity.behaviour.Behaviour;
import com.example.EcoSight.entity.behaviour.BehaviourId;
import com.example.EcoSight.entity.behaviour.LevelOfActivity;
import com.example.EcoSight.repository.BehaviourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BehaviourService {
    private final BehaviourRepository behaviourRepository;

    public void getOrCreateBehaviour(String name, LevelOfActivity levelOfActivity) {
        BehaviourId id = new BehaviourId(name, levelOfActivity);
        behaviourRepository.findById(id)
                .orElseGet(() -> {
                    Behaviour newBehaviour = new Behaviour(id);
                    return behaviourRepository.save(newBehaviour);
                });
    }
}
