package com.example.prueba2aplicacionesmoviles.network

import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String, val username: String, val role: String)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest)
}
