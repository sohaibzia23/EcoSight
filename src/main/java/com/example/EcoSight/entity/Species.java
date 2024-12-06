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
    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "common_name")
    private String commonName;
}
