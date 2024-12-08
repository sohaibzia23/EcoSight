package com.example.EcoSight.entity.behaviour;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BehaviourId {
    @Column(name = "name")
    private String name;

    @Column(name = "level_of_activity")
    @Enumerated(EnumType.STRING)
    private LevelOfActivity levelOfActivity;
}
