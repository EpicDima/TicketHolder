package com.dima.ticketholder.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not

fun Int.getViewById(): ViewInteraction = onView(withId(this))

fun ViewInteraction.click(): ViewInteraction = this.perform(ViewActions.click())

fun ViewInteraction.testDisplayed(): ViewInteraction = this.check(matches(isDisplayed()))

fun ViewInteraction.testEnabled(): ViewInteraction = this.check(matches(isEnabled()))

fun ViewInteraction.testDisabled(): ViewInteraction = this.check(matches(not(isEnabled())))

fun ViewInteraction.testChecked(): ViewInteraction = this.check(matches(isChecked()))

fun ViewInteraction.testNotChecked(): ViewInteraction = this.check(matches(isNotChecked()))
