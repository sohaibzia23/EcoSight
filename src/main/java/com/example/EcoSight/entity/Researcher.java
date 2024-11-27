package com.example.EcoSight.entity;

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
public class Researcher {

    @Id
    @Column(name="ResearcherID")
    private Integer researcherID;

    @Column(name = "REmail")
    private String rEmail;

    @Column(name = "RFirstName")
    private String rFirstName;

    @Column(name = "RLastName")
    private String rLastName;


    public Researcher(String rEmail, Integer researcherID, String rFirstName, String rLastName) {
        this.rEmail = rEmail;
        this.researcherID = researcherID;
        this.rFirstName = rFirstName;
        this.rLastName = rLastName;
    }
}
