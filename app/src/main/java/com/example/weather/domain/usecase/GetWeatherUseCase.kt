package com.example.weather.domain.usecase

import com.example.weather.domain.WeatherRepositoryApi
import com.example.weather.domain.entity.WeatherDomainModel

class GetWeatherUseCase(
    private val repository: WeatherRepositoryApi
) {
    suspend operator fun invoke(): WeatherDomainModel {
        return repository.getWeather()
    }
}