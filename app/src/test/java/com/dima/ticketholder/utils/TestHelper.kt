package com.dima.ticketholder.utils

import androidx.test.core.app.ApplicationProvider
import com.dima.ticketholder.TestApp

interface TestHelper

fun TestHelper.inject() {
    val app: TestApp = ApplicationProvider.getApplicationContext()
    app.androidInjector().inject(this)
}