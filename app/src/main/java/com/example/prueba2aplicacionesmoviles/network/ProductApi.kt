package com.example.prueba2aplicacionesmoviles.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.Body


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

    @PUT("api/productos/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body updated: ProductResponse
    ): ProductResponse

}
