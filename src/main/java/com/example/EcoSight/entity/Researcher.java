package com.example.EcoSight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="researcher")
public class Researcher {
    @Id
    @Column(name = "ResearcherID")
    private Integer researcherId;

    @Column(name = "REmail")
    private String researcherEmail;

    @Column(name = "RFirstName")
    private String researcherFirstName;

    @Column(name = "RLastName")
    private String researcherLastName;

    @OneToMany(mappedBy="approvingResearcher")
    private Set<Sighting> approvedSightings;


    public Researcher(int researcherId, String researcherEmail, String researcherFirstName, String researcherLastName) {
        this.researcherId = researcherId;
        this.researcherEmail = researcherEmail;
        this.researcherFirstName = researcherFirstName;
        this.researcherLastName = researcherLastName;
    }
}
