package com.example.EcoSight.entity.behaviour;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BehaviourId {
    private String name;
    private LevelOfActivity levelOfActivity;
}
