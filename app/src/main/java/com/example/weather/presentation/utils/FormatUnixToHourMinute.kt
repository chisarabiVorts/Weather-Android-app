package com.example.weather.presentation.utils

import androidx.compose.runtime.Composable
import java.time.Instant
@Composable
fun formatUnixToHourMinute(unixTime: Long): String {
    val date = Instant.ofEpochSecond(unixTime)
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalTime()
    return "%02d:%02d".format(date.hour, date.minute)
}