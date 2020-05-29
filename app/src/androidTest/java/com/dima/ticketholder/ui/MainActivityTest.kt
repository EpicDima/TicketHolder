package com.dima.ticketholder.ui

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dima.ticketholder.R
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
        clickOnDayNightIcon(2)
        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        clickOnDayNightIcon(2)
        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        clickOnDayNightIcon()
        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        clickOnDayNightIcon()
        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        clickOnDayNightIcon(2)
    }

    private fun clickOnDayNightIcon(n: Int = 1) {
        repeat(n) {
            onView(withId(R.id.day_night_item)).perform(click()).check(matches(isDisplayed()))
        }
    }

    private fun changeOrientation(orientation: Int) {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.requestedOrientation = orientation
        }
    }

//    @Test
//    fun clickOnRadioButtonTest() {
//        val toggleableRadioButton = onView(
//            Matchers.allOf(
//                childAtPosition(
//                    childAtPosition(
//                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
//                        0
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        toggleableRadioButton.perform(click())
//    }

//    private fun childAtPosition(
//        parentMatcher: Matcher<View>, position: Int
//    ): Matcher<View> {
//
//        return object : TypeSafeMatcher<View>() {
//            override fun describeTo(description: Description) {
//                description.appendText("Child at position $position in parent ")
//                parentMatcher.describeTo(description)
//            }
//
//            public override fun matchesSafely(view: View): Boolean {
//                val parent = view.parent
//                return parent is ViewGroup && parentMatcher.matches(parent)
//                        && view == parent.getChildAt(position)
//            }
//        }
//    }
}