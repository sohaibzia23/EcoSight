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
@Table(name="contributor")
public class Contributor {
    @Id
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "UEmail")
    private String userEmail;

    @Column(name = "UFirstName")
    private String userFirstName;

    @Column(name = "ULastName")
    private String userLastName;


    public Contributor(int id, String email, String firstName, String lastName) {
        this.userId = id;
        this.userEmail = email;
        this.userFirstName = firstName;
        this.userLastName = lastName;
    }
}