package com.example.prueba2aplicacionesmoviles.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client = OkHttpClient.Builder().build()

    fun auth(): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")   // AUTH SERVICE
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun product(): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8081/")   // PRODUCT SERVICE
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    //api del clima
    fun weather(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
