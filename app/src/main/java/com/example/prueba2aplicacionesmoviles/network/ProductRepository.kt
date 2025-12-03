package com.example.prueba2aplicacionesmoviles.network

class ProductRepository {

    private val api = RetrofitClient.product().create(ProductApi::class.java)

    suspend fun getProducts(): List<ProductResponse> = api.getProducts()

    suspend fun getProductById(id: Long): ProductResponse = api.getProductById(id)
}
