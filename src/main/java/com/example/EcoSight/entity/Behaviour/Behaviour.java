package com.example.EcoSight.entity.Behaviour;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "behaviour")
public class Behaviour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "behavior_id")
    private Integer behaviorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "activity_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelOfActivity activityLevel;
}
