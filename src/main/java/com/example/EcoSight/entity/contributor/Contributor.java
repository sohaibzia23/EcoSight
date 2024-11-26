package com.example.EcoSight.entity.contributor;

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
public class Contributor {

    @Id
    @Column(name="UserID")
    private Integer userID;

    @Column(name="UEmail")
    private String uEmail;

    @Column(name="UFirstName")
    private String uFirstName;

    @Column(name = "ULastName")
    private String uLastName;


    public Contributor(Integer userID, String uEmail, String uFirstName, String uLastName) {
        this.userID = userID;
        this.uEmail = uEmail;
        this.uFirstName = uFirstName;
        this.uLastName = uLastName;
    }






}
