package com.example.EcoSight.entity.Photo;

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

    @Column(name = "file_format")
    private String fileFormat;
}
