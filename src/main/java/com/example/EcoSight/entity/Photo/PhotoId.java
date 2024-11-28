package com.example.EcoSight.entity.Photo;

import com.example.EcoSight.entity.Sighting;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PhotoId {
    @Column(name = "PhotoID")
    private Integer phtotoId;

    @Column(name = "SightingID")
    private Integer sightingId;
}
