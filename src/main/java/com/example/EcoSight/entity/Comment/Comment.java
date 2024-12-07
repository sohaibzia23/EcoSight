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
    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false)
    private User commenter;

    @Column(name = "time_posted", nullable = false)
    private LocalDateTime timePosted;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "sighting_id", nullable = false)
    private Sighting sighting;
}
