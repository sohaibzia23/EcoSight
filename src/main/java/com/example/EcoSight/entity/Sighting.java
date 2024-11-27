package com.example.EcoSight.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name="wildlife_sightings")
public class Sighting {

    @Id
    @Column(name="SightingID")
    private Integer sightingID;


    @Column(name = "Validity")
    private Boolean validity;

    @Column(name="Time")
    private Time time;

    @Column(name="UserID")
    private Integer userID;

    @Column(name = "AdminID")
    private Integer adminID;


}
