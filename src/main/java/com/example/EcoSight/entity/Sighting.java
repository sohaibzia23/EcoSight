package com.example.EcoSight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="sighting")
public class Sighting {
    @Id
    @Column(name = "SightingID")
    private Integer sightingId;

    @Column(name = "Validity", nullable = false)
    private Boolean validity;

    @Column(name = "Time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Contributor user;

    @ManyToOne
    @JoinColumn(name = "AdminID", referencedColumnName = "AdminID")
    private Administrator admin;

    @ManyToOne
    @JoinColumn(name = "Approving_Researcher", nullable = true)
    private Researcher approvingResearcher;

    @Column(name = "Status")
    private String status;

    public Sighting(int sightingId, Boolean validity, LocalDateTime time, String status, Contributor user, Administrator admin) {
        this.sightingId = sightingId;
        this.validity = validity;
        this.time = time;
        this.status = status;
        this.user = user;
        this.admin = admin;
    }
}
