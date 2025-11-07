package com.example.weather.presentation.screen

sealed class WeatherScreenState() {

    object Init : WeatherScreenState()

    data class Loaded(val model: WeatherUiModel) : WeatherScreenState()

     class Error() : WeatherScreenState()
}