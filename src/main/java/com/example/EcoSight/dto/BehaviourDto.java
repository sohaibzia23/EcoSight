package com.example.EcoSight.dto;

import com.example.EcoSight.entity.behaviour.LevelOfActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BehaviourDto {
    private String name;
    private LevelOfActivity levelOfActivity;
}
