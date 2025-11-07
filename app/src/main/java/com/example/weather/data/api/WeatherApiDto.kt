package com.example.weather.data.api

import com.example.weather.data.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiDto {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey: String ="fa8b3df74d4042b9aa7135114252304",
        @Query("q") location: String = "Moscow",
        @Query("days") days: Int = 3
    ): WeatherResponseDto
}