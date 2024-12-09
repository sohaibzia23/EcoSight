package com.example.EcoSight.services;

import com.example.EcoSight.entity.weatherCondition.WeatherCondition;
import com.example.EcoSight.entity.weatherCondition.WeatherConditionId;
import com.example.EcoSight.entity.weatherCondition.WeatherType;
import com.example.EcoSight.repository.WeatherConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherConditionService {
    private final WeatherConditionRepository weatherConditionRepository;

    public void getOrCreateBehaviour(Double temperature, WeatherType weatherType) {
        WeatherConditionId id = new WeatherConditionId(temperature, weatherType);
        weatherConditionRepository.findById(id)
                .orElseGet(() -> {
                    WeatherCondition newWeather = new WeatherCondition(id);
                    return weatherConditionRepository.save(newWeather);
                });
    }
}
