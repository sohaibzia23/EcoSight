package com.example.EcoSight.entity.Behaviour;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ExhibitedBehaviourId {
    @Column(name = "sighting_id")
    private Integer sightingId;

    @Column(name = "species_name")
    private String speciesName;

    @Column(name = "behaviour_name")
    private String behaviourName;
}
