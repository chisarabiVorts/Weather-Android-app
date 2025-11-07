package com.example.weather.domain

import com.example.weather.domain.entity.WeatherDomainModel

interface WeatherRepositoryApi {
    suspend fun getWeather(): WeatherDomainModel
    suspend fun refreshWeather(): WeatherDomainModel
}