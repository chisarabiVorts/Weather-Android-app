package com.example.weather.presentation.screen


data class CurrentWeatherUiModel(
    val tempC: Double,
    val condition: ConditionUiModel,
    val windKph: Double,
    val windDir: String,
    val pressureMb: Double,
    val humidity: Int,
    val feelslikeC: Double,
    val visKm: Double,
    val uv: Double,
    val gustKph: Double,
)
data class ConditionUiModel(
    val icon: String,
    val code: Int
)
data class AstroUiModel(
    val sunrise: String,
    val sunset: String
)

data class HourUiModel(
    val timeEpoch: Long,
    val tempC: String,
    val icon: String,
)

data class DayUiModel(
    val maxTempC: String,
    val minTempC: String,
    val conditionCode: Int,
    val conditionIcon: String
)

data class ForecastDayUiModel(
    val dateEpoch: Long,
    val day: DayUiModel,
    val astro: AstroUiModel,
    val hours: List<HourUiModel>
)

data class LocationUiModel(
    val name: String,
    val localtimeEpoch: Long
)
data class WeatherUiModel(
    val location: LocationUiModel,
    val currentWeather: CurrentWeatherUiModel,
    val forecast: List<ForecastDayUiModel>
)