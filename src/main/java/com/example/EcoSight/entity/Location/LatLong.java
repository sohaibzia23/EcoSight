package com.example.EcoSight.entity.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LatLong {
    @Column(name="Latitude")
    Float latitude;
    @Column(name="Longitude")
    Float longitude;
}
