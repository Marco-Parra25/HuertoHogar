package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba2aplicacionesmoviles.R
import com.example.prueba2aplicacionesmoviles.viewmodel.ProductViewModel
import androidx.compose.ui.res.painterResource

@Composable
fun DetalleProducto(
    id: Long,
    onVolver: () -> Unit,
    onIrCarrito: () -> Unit,
    viewModel: ProductViewModel = viewModel()
) {
    val productos by viewModel.products.collectAsState()
    val producto = productos.find { it.id == id }

    if (producto == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Cargando producto...", color = Color.White)
            Spacer(Modifier.height(12.dp))
            Button(onClick = onVolver) {
                Text("Volver")
            }
        }
        return
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        CardConImagen(
            imagen = painterResource(id = R.drawable.tomate), // placeholder
            titulo = producto.nombre,
            descripcion = producto.descripcion,
            precio = "${producto.precio} ${producto.unidad}"
        )

        Button(
            onClick = {
                // Agrega al carrito usando tu data class Producto (la que ya tienes)
                carrito.add(
                    Producto(
                        imgRes = R.drawable.tomate,
                        titulo = producto.nombre,
                        precio = "${producto.precio}",
                        precioOferta = "${producto.precio}"
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C55E))
        ) {
            Text("Agregar al carrito 🛒")
        }

        Button(
            onClick = onIrCarrito,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir al carrito 🛒")
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C55E))
        ) {
            Text("Volver", color = Color.White)
        }
    }
}
