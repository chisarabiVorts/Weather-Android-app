package com.example.weather.data

import com.example.weather.data.api.WeatherApiDto
import com.example.weather.data.mapper.MapperFromDtoToDomainModel
import com.example.weather.di.ServiceLocator
import com.example.weather.domain.WeatherRepositoryApi
import com.example.weather.domain.entity.WeatherDomainModel

class RepositoryImpl(
    private val api: WeatherApiDto = ServiceLocator.weatherApi,
    private val mapper: MapperFromDtoToDomainModel = ServiceLocator.mapperToDomain

) : WeatherRepositoryApi {

    override suspend fun getWeather(): WeatherDomainModel {
        val responseDto = api.getWeather()
        return mapper.mapToDomain(responseDto)
    }

    override suspend fun refreshWeather(): WeatherDomainModel {
        return getWeather()
    }
}