package com.example.weather.domain.entity

data class WeatherDomainModel(
    val location: LocationModel,
    val current: CurrentWeatherModel,
    val forecast: ForecastModel
)

data class LocationModel(
    val name: String,
    val country: String,
    val localtimeEpoch: Long,
)

data class CurrentWeatherModel(
    val tempC: Double,
    val condition: ConditionModel,
    val windKph: Double,
    val windDir: String,
    val humidity: Int,
    val feelslikeC: Double,
    val pressureMb: Double,
    val visKm: Double,
    val uv: Double,
    val gustKph: Double
)

data class ConditionModel(
    val icon: String,
    val code: Int
)
data class ForecastModel(
    val forecastday: List<ForecastDayModel>
)

data class ForecastDayModel(
    val dateEpoch: Long,
    val day: DayModel,
    val astro: AstroModel,
    val hour: List<HourModel>
)

data class DayModel(
    val maxtempC: Double,
    val mintempC: Double,
    val condition: ConditionModel,
)

data class AstroModel(
    val sunrise: String,
    val sunset: String,
)

data class HourModel(
    val timeEpoch: Long,
    val tempC: Double,
    val condition: ConditionModel,
)