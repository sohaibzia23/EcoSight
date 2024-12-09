package com.example.EcoSight.dto.sighting;

import com.example.EcoSight.entity.Sighting.SightingStatus;
import lombok.Data;

@Data
public class StatusUpdateDto {
    private SightingStatus status;
}
