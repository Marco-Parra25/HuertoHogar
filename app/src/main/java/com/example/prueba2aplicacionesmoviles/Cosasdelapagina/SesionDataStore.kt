package com.example.prueba2aplicacionesmoviles.Cosasdelapagina



import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore único
val Context.dataStore by preferencesDataStore(name = "sesion_prefs")

class SesionDataStore(private val context: Context) {

    private val IS_LOGGED = booleanPreferencesKey("is_logged")

    // Leer estado de sesión
    val isLogged: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_LOGGED] ?: false }

    // Guardar estado de sesión
    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED] = value
        }
    }
}
