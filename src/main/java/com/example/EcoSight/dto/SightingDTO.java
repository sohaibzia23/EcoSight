package com.example.EcoSight.dto;

import com.example.EcoSight.entity.Sighting.SightingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightingDTO {
    private Integer sightingId;

    @NotNull(message = "Time cannot be null")
    private LocalDateTime time;

    @NotNull(message = "Contributor ID cannot be null")
    private Integer contributorId;

    @NotNull(message = "Status cannot be null")
    private SightingStatus status;
}