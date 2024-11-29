package com.example.EcoSight.entity.Photo;

import com.example.EcoSight.entity.Sighting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PhotoId {
    @Column(name = "PhotoID")
    private Integer phtotoId;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sighting;
}
