package com.example.EcoSight.entity.Comment;


import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Researcher;
import com.example.EcoSight.entity.Sighting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @EmbeddedId
    private CommentId id;

    @Column(name = "TimePosted")
    private LocalDateTime timePosted;

    @Column(name = "Text")
    private String text;

    @ManyToOne
    @MapsId("userId") // Maps this field to the composite key
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Contributor user;

    @ManyToOne
    @MapsId("researcherId") // Maps this field to the composite key
    @JoinColumn(name = "ResearcherID", referencedColumnName = "ResearcherID")
    private Researcher researcher;

    @ManyToOne
    @JoinColumn(name = "SightingID", referencedColumnName = "SightingID")
    private Sighting sighting;

    Comment(Contributor user, Researcher researcher, String text, Sighting sighting, LocalDateTime timePosted ){
        this.id = new CommentId();
        this.id.setUserId(user.getUserId());
        this.id.setResearcherId(researcher.getResearcherId());
        this.user = user;
        this.researcher = researcher;
        this.text = text;
        this.sighting = sighting;
        this.timePosted = timePosted;
    }

}
