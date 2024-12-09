package com.example.EcoSight.entity.ConservationStatus;

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
public class ConservationStatusId {

    @Column(name="conservation_status_type")
    @Enumerated(EnumType.STRING)
    private ConservationType conservationType;

    @Column(name = "conservation_description")
    private String conservationDescription;
}
