package com.example.prueba2aplicacionesmoviles.network

data class WeatherResponse(
    val current_weather: CurrentWeather
)

data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double
)
