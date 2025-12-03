package com.example.prueba2aplicacionesmoviles.network

import retrofit2.http.GET

interface WeatherApi {

    @GET("forecast?latitude=-33.45&longitude=-70.66&current_weather=true")
    suspend fun getWeather(): WeatherResponse
}

