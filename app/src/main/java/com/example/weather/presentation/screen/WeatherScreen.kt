package com.example.weather.presentation.screen

import ErrorScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.uimodel.WeatherScreenState
import com.example.weather.presentation.viewmodel.WeatherViewModel
import com.example.weather.presentation.views.LoadingScreen
import com.example.weather.presentation.views.WeatherLoadedScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val state by viewModel.stateScreen.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is WeatherScreenState.Init -> {
            LoadingScreen()
        }

        is WeatherScreenState.Loaded -> {
            WeatherLoadedScreen(
                weather = currentState.model
            )

        }

        is WeatherScreenState.Error -> {
            ErrorScreen(
                onRetry = { viewModel.refreshWeather() }
            )
        }
    }
}
