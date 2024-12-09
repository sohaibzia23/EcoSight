package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.ConservationStatusDto;
import com.example.EcoSight.entity.ConservationStatus.ConservationStatus;
import com.example.EcoSight.entity.ConservationStatus.ConservationStatusId;

public class ConservationStatusMapper {

    // Convert ConservationStatus entity to ConservationStatusDto
    public static ConservationStatusDto toDto(ConservationStatus entity) {
        if (entity == null) return null;
        return new ConservationStatusDto(
                entity.getId().getConservationType(),
                entity.getId().getConservationDescription()
        );
    }

    // Convert ConservationStatusDto to ConservationStatus entity
    public static ConservationStatus toEntity(ConservationStatusDto dto) {
        if (dto == null) return null;
        return new ConservationStatus(
                new ConservationStatusId(
                        dto.getConservationType(),
                        dto.getConservationDescription()
                )
        );
    }
}
