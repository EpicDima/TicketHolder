package com.dima.ticketholder.utils

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

const val DAY_NIGHT_MODE_KEY = "mode"

fun setDayNightModeFromPreferences(preferences: SharedPreferences) {
    AppCompatDelegate.setDefaultNightMode(preferences.getInt(DAY_NIGHT_MODE_KEY,
        AppCompatDelegate.MODE_NIGHT_NO))
}

fun changeDayNightMode(preferences: SharedPreferences) {
    var mode = AppCompatDelegate.MODE_NIGHT_NO
    if (AppCompatDelegate.getDefaultNightMode() == mode) {
        mode = AppCompatDelegate.MODE_NIGHT_YES
    }
    preferences.edit().putInt(DAY_NIGHT_MODE_KEY, mode).apply()
    AppCompatDelegate.setDefaultNightMode(mode)
}