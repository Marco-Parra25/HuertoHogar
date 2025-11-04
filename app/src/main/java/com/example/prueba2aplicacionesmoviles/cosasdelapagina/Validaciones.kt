package com.example.prueba2aplicacionesmoviles.cosasdelapagina

fun esCorreoValido(correo: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}
