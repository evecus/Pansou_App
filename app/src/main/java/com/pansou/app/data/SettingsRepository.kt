package com.pansou.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pansou_settings")

class SettingsRepository(private val context: Context) {
    companion object {
        val BASE_URL_KEY = stringPreferencesKey("base_url")
        const val DEFAULT_URL = ""
    }

    val baseUrlFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[BASE_URL_KEY] ?: DEFAULT_URL
    }

    suspend fun saveBaseUrl(url: String) {
        context.dataStore.edit { prefs ->
            prefs[BASE_URL_KEY] = url
        }
    }
}
