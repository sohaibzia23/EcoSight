package com.example.EcoSight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="species")
public class Species {
    @Id
    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "common_name")
    private String commonName;
}
