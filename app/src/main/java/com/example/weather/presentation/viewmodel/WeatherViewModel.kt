package com.example.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.RepositoryImpl
import com.example.weather.domain.usecase.GetWeatherUseCase
import com.example.weather.presentation.mapper.MapWeatherDomainToUiModel
import com.example.weather.presentation.screen.WeatherScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    private val getWeatherUseCase = GetWeatherUseCase(repository)
    private val mapper = MapWeatherDomainToUiModel()
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
            Log.d("lol", result.toString())
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

