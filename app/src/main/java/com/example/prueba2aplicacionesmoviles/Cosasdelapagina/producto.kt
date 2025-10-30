package com.example.prueba2aplicacionesmoviles.Cosasdelapagina

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Producto(
    val imgRes: Int,
    val titulo: String,
    val precio: String,
    val precioOferta: String
)

@Composable
fun CardInteractivaProducto(producto: Producto) {
    var titulo by remember(producto) { mutableStateOf(producto.titulo) }
    var precio by remember(producto) { mutableStateOf(producto.precio) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        CardConImagen(
            imagen = painterResource(id = producto.imgRes),
            titulo = titulo,
            descripcion = "Producto seleccionado",
            precio = precio,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val pFinal = producto.copy(
                    titulo = titulo,
                    precio = precio
                )
                carrito.add(pFinal)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF22C55E)
            )
        ) {
            Text("Agregar al carrito")
        }
    }
}
