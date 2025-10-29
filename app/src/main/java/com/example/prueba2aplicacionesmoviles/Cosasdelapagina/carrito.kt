package com.example.prueba2aplicacionesmoviles.Cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CarritoScreen(onVolver: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 🛒 Título principal
        Text(
            text = "🛒 Carrito de compras",
            color = Color.White, // 👈 texto blanco para fondo oscuro
            style = MaterialTheme.typography.titleLarge
        )

        // 📦 Cantidad de productos
        Text(
            text = "Productos agregados: ${carrito.size}",
            color = Color(0xFFEAB308), // Amarillo suave
            style = MaterialTheme.typography.bodyLarge
        )

        // 📭 Si está vacío
        if (carrito.isEmpty()) {
            Text(
                text = "Tu carrito está vacío.",
                color = Color(0xFF94A3B8) // Gris claro
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(carrito) { p ->
                    CardConImagen(
                        imagen = painterResource(id = p.imgRes),
                        titulo = p.titulo,
                        descripcion = "Producto en carrito",
                        precio = p.precio,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // 🔴 Botón para vaciar carrito
            Button(
                onClick = { carrito.clear() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Vaciar carrito",
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // 🔙 Botón para volver
        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Volver",
                color = Color.White
            )
        }
    }
}
