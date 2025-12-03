package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prueba2aplicacionesmoviles.network.ProductResponse
import com.example.prueba2aplicacionesmoviles.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoScreen(
    id: Long,
    onVolver: () -> Unit,
    viewModel: ProductViewModel = viewModel()
) {
    val productos by viewModel.products.collectAsState()
    val producto = productos.find { it.id == id }

    LaunchedEffect(Unit) {
        if (producto == null) viewModel.loadProducts()
    }

    val context = LocalContext.current

    if (producto == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Cargando producto...", color = Color.White)
            Spacer(Modifier.height(12.dp))
            Button(onClick = onVolver) { Text("Volver") }
        }
        return
    }

    var nombre by remember { mutableStateOf(producto.nombre) }
    var descripcion by remember { mutableStateOf(producto.descripcion) }
    var precio by remember { mutableStateOf(producto.precio.toString()) }
    var unidad by remember { mutableStateOf(producto.unidad) }
    var imagen by remember { mutableStateOf(producto.imagen) }

    // 🔥 FUNCIONA EN TODAS LAS VERSIONES DE MATERIAL3
    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.LightGray,
        cursorColor = Color.White,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = Color.LightGray,
        focusedPlaceholderColor = Color.LightGray,
        unfocusedPlaceholderColor = Color.LightGray
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            "Editar Producto",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = unidad,
            onValueChange = { unidad = it },
            label = { Text("Unidad (kg, unidad)") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("Imagen (peras.webp, limones.webp)") },
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val updated = ProductResponse(
                    id = id,
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio.toIntOrNull() ?: 0,
                    unidad = unidad,
                    imagen = imagen
                )

                viewModel.updateProduct(id, updated) { ok ->
                    if (ok) {
                        Toast.makeText(context, "Producto actualizado ✔", Toast.LENGTH_SHORT).show()
                        onVolver()
                    } else {
                        Toast.makeText(context, "Error al actualizar ❌", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF22C55E))
        ) {
            Text("Guardar Cambios")
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Cancelar")
        }
    }
}
