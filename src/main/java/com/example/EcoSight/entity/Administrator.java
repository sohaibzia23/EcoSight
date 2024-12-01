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
@Table(name="administrator")
public class Administrator {
    @Id
    @Column(name = "AdminID")
    private Integer adminID;

    @Column(name = "AEmail")
    private String adminEmail;

    @Column(name = "AFirstName")
    private String adminFirstName;

    @Column(name = "ALastName")
    private String adminLastName;

    public Administrator(int adminID, String adminEmail, String adminFirstName, String adminLastName) {
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
    }

}
