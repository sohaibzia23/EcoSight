package com.example.EcoSight.entity.behaviour;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "behaviour")
public class Behaviour {
    @EmbeddedId
    BehaviourId behaviourId;
}
