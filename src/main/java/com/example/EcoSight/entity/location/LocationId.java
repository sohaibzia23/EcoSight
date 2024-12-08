package com.example.EcoSight.entity.location;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LocationId {
    private Double latitude;
    private Double longitude;
}
