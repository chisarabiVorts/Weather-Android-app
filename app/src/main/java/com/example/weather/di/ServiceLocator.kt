package com.example.weather.di

import com.example.weather.data.RepositoryImpl
import com.example.weather.data.api.WeatherApiDto
import com.example.weather.data.mapper.MapperFromDtoToDomainModel
import com.example.weather.domain.WeatherRepositoryApi
import com.example.weather.domain.usecase.GetWeatherUseCase
import com.example.weather.presentation.mapper.MapWeatherDomainToUiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object ServiceLocator {
    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val mapperToUiModel: MapWeatherDomainToUiModel by lazy {
        MapWeatherDomainToUiModel()
    }
    val mapperToDomain: MapperFromDtoToDomainModel by lazy {
        MapperFromDtoToDomainModel()
    }

    val weatherApi: WeatherApiDto by lazy {
        retrofit.create(WeatherApiDto::class.java)
    }

    val repository: WeatherRepositoryApi by lazy {
        RepositoryImpl(weatherApi)
    }

    val getWeatherUseCase: GetWeatherUseCase by lazy {
        GetWeatherUseCase(repository)
    }

}

