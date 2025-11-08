package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.RepositoryImpl
import com.example.weather.di.ServiceLocator
import com.example.weather.domain.usecase.GetWeatherUseCase
import com.example.weather.presentation.mapper.MapWeatherDomainToUiModel
import com.example.weather.presentation.uimodel.WeatherScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase = ServiceLocator.getWeatherUseCase,
    private val mapper: MapWeatherDomainToUiModel =ServiceLocator.mapperToUiModel
) : ViewModel() {


    private val _stateScreen = MutableStateFlow<WeatherScreenState>(WeatherScreenState.Init)
    val stateScreen = _stateScreen.asStateFlow()
    private val ceh = CoroutineExceptionHandler { _, _ ->
        _stateScreen.value = WeatherScreenState.Error()

    }

    fun loadingWeather() {
        viewModelScope.launch(ceh) {
            val result = withContext(Dispatchers.IO) {
                mapper.mapToUi(getWeatherUseCase.invoke())
            }
            _stateScreen.value = WeatherScreenState.Loaded(result)
        }
    }

    fun refreshWeather() {
        _stateScreen.value = WeatherScreenState.Init
        loadingWeather()
    }

    init {
        loadingWeather()
    }

}

