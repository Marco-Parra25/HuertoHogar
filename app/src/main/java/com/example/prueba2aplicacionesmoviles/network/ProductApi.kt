package com.example.prueba2aplicacionesmoviles.network

import retrofit2.http.GET
import retrofit2.http.Path

data class ProductResponse(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val unidad: String,
    val imagen: String
)

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): ProductResponse
}
