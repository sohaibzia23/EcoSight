package com.example.EcoSight.mapping;

import com.example.EcoSight.dto.WeatherConditionDto;
import com.example.EcoSight.entity.weatherCondition.WeatherCondition;
import com.example.EcoSight.entity.weatherCondition.WeatherConditionId;

public class WeatherConditionMapper {

    public static WeatherConditionDto toDto(WeatherCondition entity) {
        if (entity == null) return null;
        return new WeatherConditionDto(
                entity.getId().getTemperature(),
                entity.getId().getWeatherType()
        );
    }

    public static WeatherCondition toEntity(WeatherConditionDto dto) {
        if (dto == null) return null;
        return new WeatherCondition(
                new WeatherConditionId(
                        dto.getTemperature(),
                        dto.getWeatherType()
                )
        );
    }
}
