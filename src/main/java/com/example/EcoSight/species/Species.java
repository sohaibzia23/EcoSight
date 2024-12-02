package com.example.EcoSight.species;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="wildlife_sightings")
public class Species {


    @Id
    @Column(name = "ScientificName")
    String scientificName;

    @Column(name = "SightingID")
    Integer sightingID;

    @Column(name="CommonName")
    String commonName;

    public Species(String commonName, String scientificName, Integer sightingID) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.sightingID = sightingID;
    }
}
