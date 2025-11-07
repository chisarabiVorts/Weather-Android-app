package com.example.weather.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weather.presentation.utils.getWeatherDescription


@Composable
fun CurrentWeatherInfo(
    currentTemp: Double,
    code: Int,
    iconUrlWeather: String,
    minTemp: String,
    maxTemp: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "${currentTemp.toInt()}°",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 100.sp
            ),
            color = Color.White
        )

        AsyncImage(
            model = "https:$iconUrlWeather",
            contentDescription = "",
            modifier = Modifier.size(64.dp)
        )

        Text(
            text = "${getWeatherDescription(code)} $minTemp°/ $maxTemp°",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}