package com.example.EcoSight.entity.Behaviour;

import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.Species;
import jakarta.persistence.*;

@Entity
@Table(name = "exhibited_behaviour" )
public class ExhibitedBehaviour {
    @EmbeddedId
    private ExhibitedBehaviourId id;

    @ManyToOne
    @MapsId("sightingId")
    @JoinColumn(name = "sighting_id")
    private Sighting sighting;

    @ManyToOne
    @MapsId("speciesName")
    @JoinColumn(name = "species_name")
    private Species species;

    @ManyToOne
    @MapsId("behaviourName")
    @JoinColumn(name = "behaviour_name")
    private Behaviour behaviour;


}
