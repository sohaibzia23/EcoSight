package com.example.EcoSight.administrator;

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
public class Administrator {

    @Id
    @Column(name = "AdminID")
    private Integer adminID;

    @Column(name = "AFirstName")
    private String adminFirstName;

    @Column(name = "ALastName")
    private String adminLastName;


    @Column(name = "AEmail")
    private String adminEmail;

    public Administrator(int adminID, String adminFirstName, String adminLastName, String adminEmail) {
        this.adminID = adminID;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminEmail = adminEmail;

    }




}
