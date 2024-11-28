package com.example.EcoSight.entity.Photo;

import com.example.EcoSight.entity.Sighting;
import jakarta.persistence.*;
import org.hibernate.engine.spi.Resolution;

@Table(name = "photo")
public class Photo {
    @EmbeddedId
    private PhotoId photoId;

    @Column(name = "Notes")
    private String notes;

    @Column(name = "Resolution")
    private String resolution;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sighting;

    Photo(Integer photoId, String notes, String resolution, Sighting sighting){
        this.photoId.setPhtotoId(photoId);
        this.photoId.setSightingId(sighting.getSightingId());
        this.notes = notes;
        this.resolution = resolution;
    }
}
