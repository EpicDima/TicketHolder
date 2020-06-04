package com.dima.ticketholder.utils

import android.app.Activity
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun <T : Activity> ActivityScenarioRule<T>.onActivity(activityScope: T.() -> Unit) =
    this.scenario.onActivity(activityScope)