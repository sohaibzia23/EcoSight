package com.example.EcoSight.entity.weatherCondition;

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
@Table(name = "weather_condition")
public class WeatherCondition {
    @EmbeddedId
    private WeatherConditionId id;
}
