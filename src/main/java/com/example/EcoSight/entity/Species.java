package com.example.EcoSight.entity;

import com.example.EcoSight.entity.Behaviour.Behaviour;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="species")
public class Species {

    @Id
    @Column(name = "ScientificName")
    private String scientificName;

    @Column(name = "CommonName")
    private String commonName;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sightingId;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "exhibits",
            joinColumns = { @JoinColumn(name = "ScientificName") },
            inverseJoinColumns = { @JoinColumn(name = "BehaviourName") }
    )
    Set<Behaviour> exhibitedBehaviour = new HashSet<>();

    Species (String scientificName, Sighting sightingId, String commonName){
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.sightingId = sightingId;
    }
}
