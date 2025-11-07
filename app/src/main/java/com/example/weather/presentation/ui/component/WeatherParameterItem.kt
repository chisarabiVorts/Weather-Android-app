package com.example.weather.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.DeviceThermostat
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.presentation.screen.WeatherUiModel
import com.example.weather.presentation.utils.WeatherIcons


@Composable
fun WeatherInfoGrid(weather: WeatherUiModel) {
    val weatherItems = listOf(
        WeatherParam(stringResource(R.string.Temperature), "${weather.currentWeather .tempC}°C", WeatherIcons.Thermostat),
        WeatherParam(stringResource(R.string.It_feels_like), "${weather.currentWeather.feelslikeC}°C", Icons.Default.DeviceThermostat),
        WeatherParam(stringResource(R.string.Wind), "${weather.currentWeather.windKph} км/ч", Icons.Default.Air),
        WeatherParam(stringResource(R.string.Pressure), "${weather.currentWeather.pressureMb} мбар", Icons.Default.Speed),
        WeatherParam(stringResource(R.string.Humidity), "${weather.currentWeather.humidity}%", Icons.Default.WaterDrop),
        WeatherParam(stringResource(R.string.Visibility), "${weather.currentWeather.visKm} км", Icons.Default.RemoveRedEye),
        WeatherParam(stringResource(R.string.UV_index), "${weather.currentWeather.uv}", Icons.Default.WbSunny)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        weatherItems.chunked(3).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    WeatherInfoItem(
                        label = item.label,
                        value = item.value,
                        icon = item.icon,
                        modifier = Modifier.weight(1f)
                    )
                }
                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun WeatherInfoItem(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                Color.Black.copy(alpha = 0.25f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 12.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
data class WeatherParam(
    val label: String,
    val value: String,
    val icon: ImageVector
)