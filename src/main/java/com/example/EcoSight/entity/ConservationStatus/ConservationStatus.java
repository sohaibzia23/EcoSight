package com.example.EcoSight.entity.ConservationStatus;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conservation_status")
public class ConservationStatus {

    @EmbeddedId
    private ConservationStatusId id;
}
