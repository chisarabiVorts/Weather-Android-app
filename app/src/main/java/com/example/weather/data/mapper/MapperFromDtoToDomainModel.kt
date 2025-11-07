package com.example.weather.data.mapper

import com.example.weather.data.dto.WeatherResponseDto
import com.example.weather.domain.entity.AstroModel
import com.example.weather.domain.entity.ConditionModel
import com.example.weather.domain.entity.CurrentWeatherModel
import com.example.weather.domain.entity.DayModel
import com.example.weather.domain.entity.ForecastDayModel
import com.example.weather.domain.entity.ForecastModel
import com.example.weather.domain.entity.HourModel
import com.example.weather.domain.entity.LocationModel
import com.example.weather.domain.entity.WeatherDomainModel

class MapperFromDtoToDomainModel {

    fun mapToDomain(dto: WeatherResponseDto): WeatherDomainModel {
        return WeatherDomainModel(
            location = LocationModel(
                name = dto.location.name,
                country = dto.location.country,
                localtimeEpoch = dto.location.localtime_epoch
            ),
            current = CurrentWeatherModel(
                tempC = dto.current.temp_c,
                condition = ConditionModel(
                    icon = dto.current.condition.icon,
                    code = dto.current.condition.code
                ),
                windKph = dto.current.wind_kph,
                windDir = dto.current.wind_dir,
                humidity = dto.current.humidity,
                feelslikeC = dto.current.feelslike_c,
                visKm = dto.current.vis_km,
                uv = dto.current.uv,
                gustKph = dto.current.gust_kph,
                pressureMb = dto.current.pressure_mb,
            ),
            forecast = ForecastModel(
                forecastday = dto.forecast.forecastday.map { fd ->
                    ForecastDayModel(
                        dateEpoch = fd.date_epoch,
                        day = DayModel(
                            maxtempC = fd.day.maxtemp_c,
                            mintempC = fd.day.mintemp_c,
                            condition = ConditionModel(
                                icon = fd.day.condition.icon,
                                code = fd.day.condition.code
                            )
                        ),
                        astro = AstroModel(
                            sunrise = fd.astro.sunrise,
                            sunset = fd.astro.sunset
                        ),
                        hour = fd.hour.map { h ->
                            HourModel(
                                timeEpoch = h.time_epoch,
                                tempC = h.temp_c,
                                condition = ConditionModel(
                                    icon = h.condition.icon,
                                    code = h.condition.code
                                )
                            )
                        }
                    )
                }
            )
        )
    }
}