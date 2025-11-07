package com.example.weather.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weather.presentation.utils.formatUnixToHourMinute


@Composable
fun HourlyForecastItem(
    time: Long,
    iconUrl: String,
    temperature: String
) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = formatUnixToHourMinute(time), style = MaterialTheme.typography.bodySmall, color = Color.White)
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = "https:$iconUrl",
                    contentDescription = "Weather icon",
                    contentScale = ContentScale.Fit,
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "$temperature°", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            }
        }
}