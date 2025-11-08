package com.example.weather.presentation.mapper

import com.example.weather.domain.entity.WeatherDomainModel
import com.example.weather.presentation.uimodel.AstroUiModel
import com.example.weather.presentation.uimodel.ConditionUiModel
import com.example.weather.presentation.uimodel.CurrentWeatherUiModel
import com.example.weather.presentation.uimodel.DayUiModel
import com.example.weather.presentation.uimodel.ForecastDayUiModel
import com.example.weather.presentation.uimodel.HourUiModel
import com.example.weather.presentation.uimodel.LocationUiModel
import com.example.weather.presentation.uimodel.WeatherUiModel


class MapWeatherDomainToUiModel {

    fun mapToUi(domain: WeatherDomainModel): WeatherUiModel {
        return WeatherUiModel(
            location = LocationUiModel(
                name = domain.location.name,
                localtimeEpoch = domain.location.localtimeEpoch
            ),
            currentWeather = CurrentWeatherUiModel(
                tempC = domain.current.tempC,
                condition = ConditionUiModel(
                    icon = domain.current.condition.icon,
                    code = domain.current.condition.code
                ),
                windKph = domain.current.windKph,
                windDir = domain.current.windDir,
                pressureMb = domain.current.pressureMb,
                humidity = domain.current.humidity,
                feelslikeC = domain.current.feelslikeC,
                visKm = domain.current.visKm,
                uv = domain.current.uv,
                gustKph = domain.current.gustKph
            ),
            forecast = domain.forecast.forecastday.map { fd ->
                ForecastDayUiModel(
                    dateEpoch = fd.dateEpoch,
                    day = DayUiModel(
                        maxTempC = fd.day.maxtempC.toInt().toString(),
                        minTempC = fd.day.mintempC.toInt().toString(),
                        conditionCode = fd.day.condition.code,
                        conditionIcon = fd.day.condition.icon
                    ),
                    astro = AstroUiModel(
                        sunrise = fd.astro.sunrise,
                        sunset = fd.astro.sunset
                    ),
                    hours = fd.hour.map { h ->
                        HourUiModel(
                            timeEpoch = h.timeEpoch,
                            tempC = h.tempC.toInt().toString(),
                            icon = h.condition.icon
                        )
                    }
                )
            }
        )
    }
}