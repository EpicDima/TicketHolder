package com.dima.ticketholder.utils

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun <T : Activity> ActivityScenarioRule<T>.onActivity(activityScope: T.() -> Unit):
        ActivityScenario<T> = this.scenario.onActivity(activityScope)