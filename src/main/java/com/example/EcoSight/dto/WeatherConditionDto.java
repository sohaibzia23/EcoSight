package com.example.EcoSight.dto;

import com.example.EcoSight.entity.weatherCondition.WeatherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherConditionDto {
    private Double temperature;
    private WeatherType weatherType;

}
