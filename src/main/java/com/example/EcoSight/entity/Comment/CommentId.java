package com.example.EcoSight.entity.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CommentId {
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "ResearcherID")
    private Integer researcherId;
}
