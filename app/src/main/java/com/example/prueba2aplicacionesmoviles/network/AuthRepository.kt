package com.example.prueba2aplicacionesmoviles.network

class AuthRepository {

    private val api = RetrofitClient.auth().create(AuthApi::class.java)

    suspend fun login(username: String, password: String): LoginResponse {
        return api.login(LoginRequest(username, password))
    }

    suspend fun register(nombre: String, email: String, password: String) {
        api.register(RegisterRequest(nombre, email, password))
    }
}
