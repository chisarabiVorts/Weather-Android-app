package com.example.weather.presentation.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatUnixToDayLabel(unixTime: Long): String {
    val date = Instant.ofEpochSecond(unixTime)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)

    return when (date) {
        today -> "Сегодня"
        tomorrow -> "Завтра"
        else -> {
            val formatter = DateTimeFormatter.ofPattern("EE", Locale("ru"))
            date.format(formatter).uppercase()
        }
    }
}

fun formatUnixToDate(unixTime: Long): String {
    val date = Instant.ofEpochSecond(unixTime)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val formatter = DateTimeFormatter.ofPattern("MM/dd")
    return date.format(formatter)
}