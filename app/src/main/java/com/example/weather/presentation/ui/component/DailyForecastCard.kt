package com.example.weather.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weather.presentation.utils.formatUnixToDate
import com.example.weather.presentation.utils.formatUnixToDayLabel


@Composable
fun DailyForecastCard(
    unixTime: Long,
    iconUrl: String,
    minTemp: String,
    maxTemp: String
) {
    val dayLabel = remember(unixTime) { formatUnixToDayLabel(unixTime) }
    val dateLabel = remember(unixTime) { formatUnixToDate(unixTime) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1.3f)
                .wrapContentWidth(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dateLabel,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
            )
            Text(
                text = dayLabel,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
            )
        }
        AsyncImage(
            model = "https:$iconUrl",
            contentDescription = "Weather icon",
            modifier = Modifier
                .weight(0.8f)
                .size(40.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "$minTemp° / $maxTemp°",
            modifier = Modifier
                .weight(1.3f)
                .wrapContentWidth(Alignment.End),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}