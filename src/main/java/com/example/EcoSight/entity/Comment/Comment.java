package com.example.EcoSight.entity.Comment;


import com.example.EcoSight.entity.Sighting.Sighting;
import com.example.EcoSight.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid;

    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false)
    private User commenter;

    @Column(name = "TimePosted", nullable = false)
    private LocalDateTime timePosted;

    @Column(name = "Text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sighting;
}
