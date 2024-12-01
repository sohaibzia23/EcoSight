package com.example.EcoSight.entity.Comment;

import com.example.EcoSight.entity.Contributor;
import com.example.EcoSight.entity.Researcher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CommentId {
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Contributor user;

    @ManyToOne
    @JoinColumn(name = "ResearcherID", referencedColumnName = "ResearcherID")
    private Researcher researcher;
}
