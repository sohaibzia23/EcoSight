package com.example.EcoSight.entity.behaviour;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "behaviour")
public class Behaviour {
    @EmbeddedId
    BehaviourId behaviourId;
}
