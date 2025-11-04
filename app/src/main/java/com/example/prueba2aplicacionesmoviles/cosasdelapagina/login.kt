package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
    var mensajeError by remember { mutableStateOf("") }

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
        // 🌿 Título
        Text(
            text = "Bienvenido a Huerto Hogar 🌿",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🖼️ Logo animado
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

        // ✉️ Campo de correo
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                showError = false
                mensajeError = ""
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

        // 🔒 Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                showError = false
                mensajeError = ""
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
                text = mensajeError,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Botón iniciar sesión
        Button(
            onClick = {
                when {
                    email.isBlank() || password.isBlank() -> {
                        showError = true
                        mensajeError = "Por favor completa todos los campos"
                    }
                    !esCorreoValido(email) -> {
                        showError = true
                        mensajeError = "El formato del correo no es válido (ej: usuario@dominio.com)"
                    }
                    else -> {
                        scope.launch {
                            dataStore.setLoggedIn(true)
                        }
                        onLoginSuccess()
                    }
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
