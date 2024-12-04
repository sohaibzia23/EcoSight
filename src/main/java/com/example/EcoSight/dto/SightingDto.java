package com.example.EcoSight.dto;

import com.example.EcoSight.entity.Sighting.SightingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightingDto {
    private Integer sightingId;
    private LocalDateTime time;
    private Integer contributorId;
    private SightingStatus status;
}