package com.dima.ticketholder.ui.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.R
import com.dima.ticketholder.utils.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFragmentUiTest {

    @Before
    fun setUp() {
        launchFragmentInContainer(null, R.style.AppTheme) {
            MainFragment.newInstance()
        }
    }

    @Test
    fun activationOfButtonsWhenClickOnRadioButtonsTest() {
        val searchButtonId = R.id.searchButton
        val resetButtonId = R.id.resetButton

        fun checkEnabled() {
            searchButtonId.getViewById().testEnabled()
            resetButtonId.getViewById().testEnabled()
        }

        fun checkDisabled() {
            searchButtonId.getViewById().testDisabled()
            resetButtonId.getViewById().testDisabled()
        }

        val indexes = listOf(Pair(0, 2), Pair(1, 1), Pair(2, 1), Pair(2, 2), Pair(0, 0))

        indexes.forEach { (rowIndex, colIndex) ->
            checkDisabled()
            onInputRadioButton(rowIndex, colIndex).testNotChecked().click().testChecked()
            checkEnabled()
            onInputRadioButton(rowIndex, colIndex).testChecked().click().testNotChecked()
            checkDisabled()
        }
    }

    private fun onInputRadioButton(rowIndex: Int, colIndex: Int): ViewInteraction = Espresso.onView(
        childAtPosition(
            childAtPosition(
                childAtPosition(ViewMatchers.withId(R.id.inputLayout), 0), rowIndex
            ), colIndex
        )
    )
}