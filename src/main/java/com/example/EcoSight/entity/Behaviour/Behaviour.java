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
    @Column(name = "BehaviourName")
    private String name;

    @Column(name = "Style")
    private String style;

    @Enumerated(EnumType.STRING)
    @Column(name = "LevelOfActivity")
    private LevelOfActivity levelOfActivity;

    public Behaviour(String name, String style, LevelOfActivity levelOfActivity){
        this.name = name;
        this.style = style;
        this.levelOfActivity = levelOfActivity;
    }
}
