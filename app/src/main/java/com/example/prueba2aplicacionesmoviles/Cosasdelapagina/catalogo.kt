package com.example.prueba2aplicacionesmoviles.Cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Catalogo(
    onVerDetalle: (Int) -> Unit,   // 👉 se usa para navegar al detalle
    onIrCarrito: () -> Unit,       // 👉 nueva lambda para ir al carrito
    onVolver: () -> Unit           // 👉 nueva lambda para volver atrás
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔹 Mostrar el contador del carrito
        Text(
            text = "Carrito: ${carrito.size} productos",
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // 🔹 Lista de productos del demo
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(productosDemo) { index, p ->
                CardConImagen(
                    imagen = painterResource(id = p.imgRes),
                    titulo = p.titulo,
                    descripcion = "Producto del catálogo",
                    precio = p.precio,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(6.dp))
                Button(
                    onClick = { onVerDetalle(index) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver detalle")
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🛒 Ir al carrito
        Button(
            onClick = onIrCarrito,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir al carrito 🛒")
        }

        Spacer(Modifier.height(8.dp))

        // 🔙 Botón para volver
        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver", color = Color.White)
        }
    }
}
