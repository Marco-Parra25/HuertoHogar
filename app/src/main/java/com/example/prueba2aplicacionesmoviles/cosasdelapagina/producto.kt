package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    // ⚙️ Contexto para obtener vibrator
    val context = LocalContext.current
    val vibrator = remember {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as android.os.VibratorManager
            manager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

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

                // 🔔 Vibración al agregar producto
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100, // duración
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(100)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF22C55E) // verde visible
            )
        ) {
            Text("Agregar al carrito")
        }
    }
}
