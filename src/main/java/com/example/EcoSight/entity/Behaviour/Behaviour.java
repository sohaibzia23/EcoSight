package com.example.EcoSight.entity.Behaviour;

import com.example.EcoSight.entity.Species;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "exhibitedBehaviour")
    private Set<Species> species = new HashSet<>();

    public Behaviour(String name, String style, LevelOfActivity levelOfActivity){
        this.name = name;
        this.style = style;
        this.levelOfActivity = levelOfActivity;
    }
}
