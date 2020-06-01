package com.dima.ticketholder.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.dima.ticketholder.utils.changeDayNightMode
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val preferences: SharedPreferences) :
    ViewModel() {

    fun changeDayNightMode() {
        changeDayNightMode(preferences)
    }
}