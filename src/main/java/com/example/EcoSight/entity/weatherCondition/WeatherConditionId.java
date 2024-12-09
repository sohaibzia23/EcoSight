package com.example.EcoSight.entity.weatherCondition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class WeatherConditionId{
    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "weather_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WeatherType weatherType;
}
