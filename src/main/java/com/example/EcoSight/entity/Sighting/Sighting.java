package com.example.EcoSight.entity.Sighting;

import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Species;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sighting")
public class Sighting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sighting_id")
    private Integer sightingId;

    @Column(name = "sighting_time", nullable = false)
    private LocalDateTime sightingTime;

    @ManyToOne
    @JoinColumn(name = "contributor_id", nullable = false)
    private Contributor contributor;

    @ManyToOne
    @JoinColumn(name = "scientific_name", nullable = false)
    private Species species;

    @Column(name = "Status", nullable = false)
    private SightingStatus status;
}
