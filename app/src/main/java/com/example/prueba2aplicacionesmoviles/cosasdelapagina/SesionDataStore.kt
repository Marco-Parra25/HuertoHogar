package com.example.prueba2aplicacionesmoviles.cosasdelapagina

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "sesion_prefs")

class SesionDataStore(private val context: Context) {

    private val IS_LOGGED = booleanPreferencesKey("is_logged")
    private val TOKEN = stringPreferencesKey("token")

    val isLogged: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_LOGGED] ?: false }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED] = value
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN] = token
        }
    }

    suspend fun getToken(): String {
        val prefs = context.dataStore.data.map { it }.first()
        return prefs[TOKEN] ?: ""
    }
}
