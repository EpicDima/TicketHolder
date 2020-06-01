package com.dima.ticketholder.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.R
import com.dima.ticketholder.utils.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnDayNightIconTest() {
        val clicks = intArrayOf(2, 2, 1, 1, 2)
        fun getCurrentMode() = AppCompatDelegate.getDefaultNightMode()
        var currentMode = getCurrentMode()
        lateinit var preferences: SharedPreferences
        activityScenarioRule.scenario.onActivity {
            preferences = it.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        }
        clicks.forEach { clickCount ->
            repeat(clickCount) {
                R.id.day_night_item.getViewById().click().testDisplayed()
                assertNotEquals(currentMode, getCurrentMode())
                currentMode = getCurrentMode()
                assertEquals(
                    preferences
                        .getInt(DAY_NIGHT_MODE_KEY, AppCompatDelegate.MODE_NIGHT_NO), currentMode
                )
            }
            rotateScreen()
        }
    }

    private fun rotateScreen() {
        activityScenarioRule.scenario.onActivity { activity ->
            if (activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }
    }
}