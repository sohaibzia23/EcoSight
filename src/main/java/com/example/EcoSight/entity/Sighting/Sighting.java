package com.example.EcoSight.entity.Sighting;

import com.example.EcoSight.entity.location.Location;
import com.example.EcoSight.entity.Species;
import com.example.EcoSight.entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    private User contributor;

    @ManyToOne
    @JoinColumn(name = "scientific_name", nullable = false)
    private Species species;

    @ElementCollection
    @CollectionTable(name = "sighting_images", joinColumns = @JoinColumn(name = "sighting_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SightingStatus status = SightingStatus.PENDING;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "latitude", referencedColumnName = "latitude", nullable = false),
            @JoinColumn(name = "longitude", referencedColumnName = "longitude", nullable = false)
    })
    private Location location;
}
