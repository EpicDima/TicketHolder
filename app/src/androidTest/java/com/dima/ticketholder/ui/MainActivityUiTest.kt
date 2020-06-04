package com.dima.ticketholder.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.R
import com.dima.ticketholder.utils.click
import com.dima.ticketholder.utils.getViewById
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUiTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnDayNightIconTest() {
        val clicks = intArrayOf(1, 1, 2)
        fun getCurrentMode() = AppCompatDelegate.getDefaultNightMode()
        var currentMode = getCurrentMode()
        clicks.forEach { clickCount ->
            repeat(clickCount) {
                R.id.day_night_item.getViewById().click()
                assertNotEquals(currentMode, getCurrentMode())
                currentMode = getCurrentMode()
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