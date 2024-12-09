package com.example.EcoSight.services;

import com.example.EcoSight.entity.ConservationStatus.ConservationStatus;
import com.example.EcoSight.entity.ConservationStatus.ConservationStatusId;
import com.example.EcoSight.entity.ConservationStatus.ConservationType;
import com.example.EcoSight.repository.ConservationStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConservationStatusService {

    private final ConservationStatusRepository conservationStatusRepository;

    public void getOrCreateConservationStatus(ConservationType conservationType, String conservationDescription) {
        ConservationStatusId id = new ConservationStatusId(conservationType, conservationDescription);
        conservationStatusRepository.findById(id)
                .orElseGet(() -> {
                    ConservationStatus newConservation = new ConservationStatus(id);
                    return conservationStatusRepository.save(newConservation);
                });
    }
}
