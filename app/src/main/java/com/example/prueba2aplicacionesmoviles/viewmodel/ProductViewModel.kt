package com.example.prueba2aplicacionesmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba2aplicacionesmoviles.network.ProductApi
import com.example.prueba2aplicacionesmoviles.network.ProductResponse
import com.example.prueba2aplicacionesmoviles.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    // 🔥 Instancia del servicio Retrofit
    private val api = RetrofitClient.product().create(ProductApi::class.java)

    // Lista de productos cargados
    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> = _products

    // Indicador de carga
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // 🔥 Cargar productos
    fun loadProducts() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _products.value = api.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    // 🔥 Editar producto (PUT)
    fun updateProduct(id: Long, updated: ProductResponse, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.updateProduct(id, updated)

                // Actualiza la lista local
                _products.value = _products.value.map {
                    if (it.id == id) response else it
                }

                onResult(true)

            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }
}
