package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Behaviour.Behaviour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface BehaviourRepository extends JpaRepository<Behaviour, String> {

    List<Behaviour> findBehaviourBySpecies(String scientificName);

}
