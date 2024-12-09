package com.example.EcoSight.dto;

import com.example.EcoSight.entity.ConservationStatus.ConservationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConservationStatusDto {

    private ConservationType conservationType;
    private String conservationDescription;
}
