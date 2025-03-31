package com.moviescatalog.core.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreferenceManager {
    private val Context.dataStore by preferencesDataStore(name = PreferenceKeys.PREF_NAME)

    suspend fun saveCinemaMode(context: Context, isCinemaMode: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.CINEMA_MODE_KEY] = isCinemaMode
        }
    }

    fun getCinemaModeFlow(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { prefs -> prefs[PreferenceKeys.CINEMA_MODE_KEY] ?: true }
    }
}