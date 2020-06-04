package com.dima.ticketholder.ui

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.utils.DAY_NIGHT_MODE_KEY
import com.dima.ticketholder.utils.TestHelper
import com.dima.ticketholder.utils.inject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@LargeTest
@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest : TestHelper {

    companion object {
        const val DAY_MODE = AppCompatDelegate.MODE_NIGHT_NO
        const val NIGHT_MODE = AppCompatDelegate.MODE_NIGHT_YES
    }

    init {
        inject()
    }

    @Inject
    lateinit var preferences: SharedPreferences

    @Test
    fun changeDayNightModeTest() {
        val viewModel = MainActivityViewModel(preferences)

        assertEquals(0, preferences.getInt(DAY_NIGHT_MODE_KEY, 0))
        viewModel.changeDayNightMode()
        assertEquals(NIGHT_MODE, preferences.getInt(DAY_NIGHT_MODE_KEY, 0))
        viewModel.changeDayNightMode()
        assertEquals(DAY_MODE, preferences.getInt(DAY_NIGHT_MODE_KEY, 0))
        viewModel.changeDayNightMode()
        assertEquals(NIGHT_MODE, preferences.getInt(DAY_NIGHT_MODE_KEY, 0))
    }
}