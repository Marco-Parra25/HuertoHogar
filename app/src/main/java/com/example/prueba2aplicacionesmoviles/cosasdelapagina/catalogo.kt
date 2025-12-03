package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba2aplicacionesmoviles.R
import com.example.prueba2aplicacionesmoviles.network.ProductResponse
import com.example.prueba2aplicacionesmoviles.viewmodel.ProductViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun Catalogo(
    onVerDetalle: (Long) -> Unit,
    onIrCarrito: () -> Unit,
    onVolver: () -> Unit,
    viewModel: ProductViewModel = viewModel()
) {

    val productos by viewModel.products.collectAsState()
    val loading by viewModel.loading.collectAsState()

    // Cargar productos solo una vez
    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Carrito: ${carrito.size} productos",
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // 🔄 LOADING
        if (loading && productos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        // ❌ SIN PRODUCTOS
        else if (productos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay productos disponibles", color = Color.White)
            }
        }
        // 📋 LISTA DE PRODUCTOS
        else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {

                items(productos) { p: ProductResponse ->

                    // 🔥 Extraer nombre real desde "/fotos/limones.webp"
                    val fileName = p.imagen
                        .substringAfterLast("/")   // limones.webp
                        .substringBefore(".")      // limones

                    // 🔥 Mapeo correcto según tu drawable
                    val imageRes = when (fileName.lowercase()) {
                        "peras" -> R.drawable.peras
                        "limones" -> R.drawable.limones
                        "lechuga" -> R.drawable.lechuga
                        else -> R.drawable.tomate // fallback
                    }

                    // 🖼 Tarjeta con imagen correcta
                    CardConImagen(
                        imagen = painterResource(id = imageRes),
                        titulo = p.nombre,
                        descripcion = p.descripcion,
                        precio = "${p.precio} ${p.unidad}",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(6.dp))

                    Button(
                        onClick = { onVerDetalle(p.id) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF22C55E)
                        )
                    ) {
                        Text("Ver detalle")
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onIrCarrito,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF22C55E)
                )
            ) {
                Text("Ir al carrito 🛒")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF22C55E)
                )
            ) {
                Text("Volver", color = Color.White)
            }
        }
    }
}
