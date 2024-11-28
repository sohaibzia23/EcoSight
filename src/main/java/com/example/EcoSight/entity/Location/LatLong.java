package com.example.EcoSight.entity.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class LatLong {
    @Column(name="Latitude")
    Float latitude;
    @Column(name="Longitude")
    Float longitude;

    public LatLong(Float lattitude, Float longitude) {
    }
}
