package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba2aplicacionesmoviles.R
import kotlinx.coroutines.launch

@Composable
fun NavegacionPantallas(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { SesionDataStore(context) }

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // 🟢 LOGIN
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onRegister = { navController.navigate("register") }
            )
        }

        // 📝 REGISTER
        composable("register") {
            RegisterScreen { navController.popBackStack() }
        }

        // 🏡 HOME
        composable("home") {
            HomeMenu(
                onOpenCatalogo = { navController.navigate("catalogo") },
                onOpenCarrito = { navController.navigate("carrito") },
                onOpenCamara = { navController.navigate("camara") },
                onLogout = {
                    scope.launch {
                        dataStore.setLoggedIn(false)
                    }
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // 📷 CÁMARA
        composable("camara") {
            CamaraCapture()
        }

        // 🛒 CATÁLOGO
        composable("catalogo") {
            Catalogo(
                onVerDetalle = { idx ->
                    navController.navigate("detalle/$idx")
                },
                onIrCarrito = { navController.navigate("carrito") },
                onVolver = { navController.popBackStack() }
            )
        }

        // 📦 DETALLE PRODUCTO
        composable("detalle/{index}") { backStackEntry ->
            val idx = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            DetalleProducto(
                index = idx,
                onVolver = { navController.popBackStack() },
                onIrCarrito = { navController.navigate("carrito") }
            )
        }

        // 🛍️ CARRITO
        composable("carrito") {
            CarritoScreen { navController.popBackStack() }
        }
    }
}

@Composable
fun HomeMenu(
    onOpenCatalogo: () -> Unit,
    onOpenCarrito: () -> Unit,
    onOpenCamara: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🌿 Título principal
        Text(
            text = "Bienvenidos a Huerto Hogar",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

        // 🖼️ Imagen debajo del texto
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Huerto Hogar",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🪴 Botones del Home
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                listOf(
                    "Catálogo" to onOpenCatalogo,
                    "Carrito de compras" to onOpenCarrito,
                    "Abrir cámara" to onOpenCamara
                )
            ) { (label, action) ->
                Button(
                    onClick = action,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(label)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔐 Botón para cerrar sesión
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48))
        ) {
            Text("Cerrar sesión", color = Color.White)
        }
    }
}
