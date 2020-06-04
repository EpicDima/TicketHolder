package com.dima.ticketholder.ui

import android.os.Build
import android.view.MenuItem
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.R
import com.dima.ticketholder.utils.TestHelper
import com.dima.ticketholder.utils.inject
import com.dima.ticketholder.utils.onActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@LargeTest
@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestHelper {

    init {
        inject()
    }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var viewModel: MainActivityViewModel

    @Test
    fun onOptionsItemSelectedDayNightItemTest() {
        val dayNightMenuItem = mockk<MenuItem> {
            every { itemId } returns R.id.day_night_item
        }
        activityScenarioRule.onActivity {
            onOptionsItemSelected(dayNightMenuItem)
        }
        verify(exactly = 1) {
            viewModel.changeDayNightMode()
        }
    }

    @Test
    fun onOptionsItemSelectedUnknownItemTest() {
        val unknownMenuItem = mockk<MenuItem> {
            every { itemId } returns 0
        }
        activityScenarioRule.onActivity {
            onOptionsItemSelected(unknownMenuItem)
        }
        verify(exactly = 0) {
            viewModel.changeDayNightMode()
        }
    }
}