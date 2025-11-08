package com.example.weather.presentation.utils


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R

@Composable
fun getWeatherDescription(code: Int): String {
    return when (code) {
        1000 -> stringResource(R.string.clearly)
        1003, 1006, 1009 -> stringResource(R.string.cloudy)
        1063, 1150, 1180, 1183, 1186 -> stringResource(R.string.rain)
        1066, 1114, 1210 -> stringResource(R.string.snow)
        1087, 1273, 1276 -> stringResource(R.string.thunder)
        1030, 1135, 1147 -> stringResource(R.string.fog)
        1130 -> stringResource(R.string.light_fog)
        else -> stringResource(R.string.unknown)
    }
}