package com.example.weather.data

import com.example.weather.data.api.RetrofitInstance
import com.example.weather.data.api.WeatherApiDto
import com.example.weather.data.mapper.MapperFromDtoToDomainModel
import com.example.weather.domain.WeatherRepositoryApi
import com.example.weather.domain.entity.WeatherDomainModel

class RepositoryImpl(

) : WeatherRepositoryApi {

    private val api: WeatherApiDto = RetrofitInstance.api
    private val mapper = MapperFromDtoToDomainModel()
    override suspend fun getWeather(): WeatherDomainModel {
        val responseDto = api.getWeather()
        return mapper.mapToDomain(responseDto)
    }

    override suspend fun refreshWeather(): WeatherDomainModel {
        return getWeather()
    }
}