package com.example.EcoSight.repository;

import com.example.EcoSight.entity.weatherCondition.WeatherCondition;
import com.example.EcoSight.entity.weatherCondition.WeatherConditionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherConditionRepository extends JpaRepository<WeatherCondition, WeatherConditionId> {

}
