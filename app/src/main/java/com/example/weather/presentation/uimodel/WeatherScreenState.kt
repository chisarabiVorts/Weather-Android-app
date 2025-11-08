package com.example.weather.presentation.uimodel

sealed class WeatherScreenState() {

    object Init : WeatherScreenState()

    data class Loaded(val model: WeatherUiModel) : WeatherScreenState()

     class Error() : WeatherScreenState()
}