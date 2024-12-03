package com.example.EcoSight.entity.Location;

import com.example.EcoSight.entity.Sighting.Sighting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @EmbeddedId
    private LatLong latLong;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sighting;
}
