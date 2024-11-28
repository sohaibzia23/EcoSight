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
@Table(name="researcher")
public class Researcher {
    @Id
    @Column(name = "researcherID")
    private Integer researcherId;

    @Column(name = "REmail")
    private String researcherEmail;

    @Column(name = "RFirstName")
    private String researcherFirstName;

    @Column(name = "RLastName")
    private String researcherLastName;


    public Researcher(int researcherId, String researcherEmail, String researcherFirstName, String researcherLastName) {
        this.researcherId = researcherId;
        this.researcherEmail = researcherEmail;
        this.researcherFirstName = researcherFirstName;
        this.researcherLastName = researcherLastName;
    }
}
