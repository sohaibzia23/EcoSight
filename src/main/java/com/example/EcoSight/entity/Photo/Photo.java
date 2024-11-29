package com.example.EcoSight.entity.Photo;

import com.example.EcoSight.entity.Sighting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {
    @EmbeddedId
    private PhotoId photoId;

    @Column(name = "Notes")
    private String notes;

    @Column(name = "Resolution")
    private String resolution;

    Photo(Integer photoId, String notes, String resolution, Sighting sighting){
        this.photoId.setPhtotoId(photoId);
        this.photoId.setSighting(sighting);
        this.notes = notes;
        this.resolution = resolution;
    }
}
