package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba2aplicacionesmoviles.viewmodel.WeatherViewModel

@Composable
fun ClimaScreen(onVolver: () -> Unit, viewModel: WeatherViewModel = viewModel()) {

    val weather by viewModel.weather.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Clima actual - Santiago", color = Color.White)

        Spacer(Modifier.height(20.dp))

        if (weather == null) {
            Text("Cargando clima...", color = Color.White)
        } else {
            Text("Temperatura: ${weather!!.current_weather.temperature}°C", color = Color.White)
            Text("Viento: ${weather!!.current_weather.windspeed} km/h", color = Color.White)
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = onVolver) { Text("Volver") }
    }
}
