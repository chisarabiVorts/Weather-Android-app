package com.example.weather.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather.presentation.ui.component.CurrentWeatherInfo
import com.example.weather.presentation.ui.component.DailyForecastCard
import com.example.weather.presentation.ui.component.HourlyForecastItem
import com.example.weather.presentation.ui.component.SunProgressBar
import com.example.weather.presentation.ui.component.WeatherInfoGrid
import com.example.weather.presentation.uimodel.ForecastDayUiModel
import com.example.weather.presentation.uimodel.HourUiModel
import com.example.weather.presentation.uimodel.WeatherUiModel
import com.example.weather.presentation.utils.WeatherIcons
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherLoadedScreen(
    weather: WeatherUiModel,
) {

    val skyGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF74B9FF),
            Color(0xFF0984E3)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(skyGradient),
    ) {
        Column {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = weather.location.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Icon(
                            imageVector = WeatherIcons.Place,
                            contentDescription = "Refresh",
                            tint = Color.White,
                            modifier = Modifier.size(10.dp)
                        )

                    }

                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = WeatherIcons.Receipt,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = WeatherIcons.Brightness,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                expandedHeight = 64.dp,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    CurrentWeatherInfo(
                        currentTemp = weather.currentWeather.tempC,
                        code = weather.currentWeather.condition.code,
                        iconUrlWeather = weather.currentWeather.condition.icon,
                        minTemp = weather.forecast[0].day.minTempC,
                        maxTemp = weather.forecast[0].day.maxTempC
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(horizontal = 8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.25f),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            val startOfDay = LocalDate.now().atStartOfDay()
                            val endOfDay = startOfDay.plusDays(1)

                            val remainingHours = weather.forecast[0].hours.filter { hour ->
                                val hourTime = LocalDateTime.ofInstant(
                                    Instant.ofEpochSecond(hour.timeEpoch),
                                    ZoneId.systemDefault()
                                )
                                !hourTime.isBefore(startOfDay) && hourTime.isBefore(endOfDay)
                            }
                            items(remainingHours) { hour ->
                                HourlyForecastItem(
                                    time = hour.timeEpoch,
                                    iconUrl = hour.icon,
                                    temperature = hour.tempC
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.25f),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    ) {
                        weather.getForecastDays().forEach { forecastDay ->
                            DailyForecastCard(
                                unixTime = forecastDay.dateEpoch,
                                iconUrl = forecastDay.day.conditionIcon,
                                minTemp = forecastDay.day.minTempC,
                                maxTemp = forecastDay.day.maxTempC
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherInfoGrid(
                        weather = weather
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(all = 8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.25f),
                                shape = RoundedCornerShape(16.dp)
                            ),
                    ) {
                        SunProgressBar(
                            sunrisTime = weather.forecast[0].astro.sunrise,
                            sunsTime = weather.forecast[0].astro.sunset,
                        )
                    }

                }
            }
        }
    }
}

private fun WeatherUiModel.getForecastDays(): List<ForecastDayUiModel> {
    return this.forecast
}