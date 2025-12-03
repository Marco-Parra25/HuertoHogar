package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba2aplicacionesmoviles.R
import com.example.prueba2aplicacionesmoviles.viewmodel.ProductViewModel

@Composable
fun DetalleProducto(
    id: Long,
    onVolver: () -> Unit,
    onIrCarrito: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: ProductViewModel = viewModel()
) {
    // 🔥 Cargar productos si no existen
    LaunchedEffect(Unit) {
        if (viewModel.products.value.isEmpty()) {
            viewModel.loadProducts()
        }
    }

    val productos by viewModel.products.collectAsState()
    val producto = productos.find { it.id == id }

    if (producto == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
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

    // 🔥 MAPEO REAL DE IMAGEN SEGÚN NOMBRE O IMAGEN DEL BACKEND
    val imageRes = when (producto.imagen.lowercase()) {
        "peras", "/fotos/peras.webp" -> R.drawable.peras
        "limones", "/fotos/limones.webp" -> R.drawable.limones
        "lechuga", "/fotos/lechuga.webp" -> R.drawable.lechuga
        "platano", "/fotos/platano.webp" -> R.drawable.platano
        "tomate", "/fotos/tomate.webp" -> R.drawable.tomate
        else -> R.drawable.tomate
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // --- Imagen y datos del producto ---
        CardConImagen(
            imagen = painterResource(id = imageRes),
            titulo = producto.nombre,
            descripcion = producto.descripcion,
            precio = "${producto.precio} ${producto.unidad}"
        )

        // --- Agregar al carrito ---
        Button(
            onClick = {
                carrito.add(
                    Producto(
                        imgRes = imageRes,
                        titulo = producto.nombre,
                        precio = "${producto.precio}",
                        precioOferta = "${producto.precio}"
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF22C55E)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar al carrito 🛒")
        }

        // --- Ir al carrito ---
        Button(
            onClick = onIrCarrito,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir al carrito 🛒")
        }

        // --- 🔥 EDITAR PRODUCTO ---
        Button(
            onClick = { onEdit(id) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFFF59E0B))
        ) {
            Text("Editar producto ✏️", color = Color.White)
        }

        // --- Volver ---
        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFFE11D48))
        ) {
            Text("Volver", color = Color.White)
        }
    }
}
