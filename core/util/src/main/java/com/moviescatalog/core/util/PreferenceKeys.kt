package com.moviescatalog.core.util

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    const val PREF_NAME = "settings"
    val CINEMA_MODE_KEY = booleanPreferencesKey("cinema_mode")
}