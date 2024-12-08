package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.BehaviourDto;
import com.example.EcoSight.entity.behaviour.Behaviour;
import com.example.EcoSight.entity.behaviour.BehaviourId;

public class BehaviourMapper {

    public static BehaviourDto toDto(Behaviour behaviour) {
        if (behaviour == null) {
            return null;
        }

        return new BehaviourDto(
                behaviour.getBehaviourId().getName(),
                behaviour.getBehaviourId().getLevelOfActivity()
        );
    }

    public static Behaviour toEntity(BehaviourDto dto) {
        if (dto == null) {
            return null;
        }

        Behaviour behaviour = new Behaviour();
        behaviour.setBehaviourId(
                new BehaviourId(
                        dto.getName(),
                        dto.getLevelOfActivity()
                )
        );

        return behaviour;
    }
}
