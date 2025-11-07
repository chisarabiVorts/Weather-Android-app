package com.example.weather.presentation.utils

fun getWeatherDescription(code: Int): String {
    return when (code) {
        1000 -> "Ясно"
        1003, 1006, 1009 -> "Облачно"
        1063, 1150, 1180, 1183, 1186 -> "Дождь"
        1066, 1114, 1210 -> "Снег"
        1087, 1273, 1276 -> "Гроза"
        1030, 1135, 1147 -> "Туман"
        1130 -> "Легкий туман"
        else -> "Неизвестно"
    }
}