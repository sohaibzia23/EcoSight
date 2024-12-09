package com.example.EcoSight.repository;

import com.example.EcoSight.entity.ConservationStatus.ConservationStatus;
import com.example.EcoSight.entity.ConservationStatus.ConservationStatusId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservationStatusRepository extends JpaRepository<ConservationStatus, ConservationStatusId>{

}
