package com.example.EcoSight.services;


import com.example.EcoSight.entity.Behaviour.Behaviour;
import com.example.EcoSight.repository.BehaviourRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component

public class BehaviourService {

    private final BehaviourRepository behaviourRepository;

    public Behaviour addBehaviour(Behaviour behaviour) {
        return behaviourRepository.save(behaviour);
    }

    public void deleteBehaviour(String name){
        behaviourRepository.deleteById(name);
    }


}
