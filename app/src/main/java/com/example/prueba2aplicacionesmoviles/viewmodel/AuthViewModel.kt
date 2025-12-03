package com.example.prueba2aplicacionesmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba2aplicacionesmoviles.network.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repo = AuthRepository()

    private val _loginState = MutableStateFlow<String?>(null)
    val loginState = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repo.login(username, password)
                _loginState.value = response.token  // ✔ token real
            } catch (e: Exception) {
                _loginState.value = "ERROR"
            }
        }
    }
}
