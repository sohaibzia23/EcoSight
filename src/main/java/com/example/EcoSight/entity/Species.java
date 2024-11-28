package com.example.EcoSight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    Species (String scientificName, Sighting sightingId, String commonName){
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.sightingId = sightingId;
    }
}
