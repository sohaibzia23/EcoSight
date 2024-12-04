package com.example.EcoSight.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationDto {
    private Float latitude;
    private Float longitude;
    private Integer sightingId;
}