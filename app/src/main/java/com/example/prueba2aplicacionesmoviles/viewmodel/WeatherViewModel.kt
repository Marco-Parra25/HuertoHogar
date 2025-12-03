package com.example.prueba2aplicacionesmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba2aplicacionesmoviles.network.RetrofitClient
import com.example.prueba2aplicacionesmoviles.network.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.prueba2aplicacionesmoviles.network.WeatherApi


class WeatherViewModel : ViewModel() {

    private val api = RetrofitClient.weather().create(WeatherApi::class.java)

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    fun loadWeather() {
        viewModelScope.launch {
            try {
                _weather.value = api.getWeather()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
