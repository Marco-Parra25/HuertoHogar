package com.example.prueba2aplicacionesmoviles.Cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalleProducto(index: Int, onVolver: () -> Unit,
                    onIrCarrito: () -> Unit) {
    val p = productosDemo.getOrElse(index) { productosDemo.first() }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Interactiva para ese producto
        CardInteractivaProducto(p)

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
        Button(
            onClick = onIrCarrito,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir al carrito 🛒")
        }
    }
    }




