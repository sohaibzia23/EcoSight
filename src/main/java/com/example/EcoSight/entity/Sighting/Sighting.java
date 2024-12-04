package com.example.EcoSight.entity.Sighting;

import com.example.EcoSight.entity.Contributor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="sighting")
public class Sighting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SightingID")
    private Integer sightingId;

    @Column(name = "Time", nullable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "contributor_id", nullable = false)
    private Contributor contributor;

    @Column(name = "Status", nullable = false)
    private SightingStatus status;
}
