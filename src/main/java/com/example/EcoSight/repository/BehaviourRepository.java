package com.example.EcoSight.repository;

import com.example.EcoSight.entity.behaviour.Behaviour;
import com.example.EcoSight.entity.behaviour.BehaviourId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BehaviourRepository extends JpaRepository<Behaviour, BehaviourId> {
}
