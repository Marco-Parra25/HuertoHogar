package com.example.prueba2aplicacionesmoviles.Cosasdelapagina

import android.app.Application
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.prueba2aplicacionesmoviles.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegister: () -> Unit
) {
    val context = LocalContext.current
    val dataStore = remember { SesionDataStore(context) }
    val scope = rememberCoroutineScope()
    val isLogged by dataStore.isLogged.collectAsState(initial = false)

    // Si ya está logueado, ir directo al Home
    LaunchedEffect(isLogged) {
        if (isLogged) {
            onLoginSuccess()
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val camposValidos = email.isNotBlank() && password.isNotBlank()
    val scroll = rememberScrollState()

    var logoVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { logoVisible = true }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Huerto Hogar 🌿",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = logoVisible,
            enter = fadeIn(tween(700)) + scaleIn(tween(700)),
            exit = fadeOut(tween(300))
        ) {
            Image(
                painter = painterResource(id = R.drawable.huerto),
                contentDescription = "Logo Huerto Hogar",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                showError = false
            },
            label = { Text("Correo electrónico", color = Color.White) },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                showError = false
            },
            label = { Text("Contraseña", color = Color.White) },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (showError) {
            Text(
                text = "Por favor completa todos los campos antes de continuar",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Botón iniciar sesión
        Button(
            onClick = {
                if (camposValidos) {
                    scope.launch {
                        dataStore.setLoggedIn(true) // Guarda la sesión
                    }
                    onLoginSuccess()
                } else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C55E))
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 📝 Botón registrarse
        Button(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8))
        ) {
            Text("Registrarse")
        }
    }
}
