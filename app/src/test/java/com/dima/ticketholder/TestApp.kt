package com.dima.ticketholder

import com.dima.ticketholder.di.DaggerTestAppComponent

class TestApp : App() {
    override fun initDagger() {
        DaggerTestAppComponent.builder()
            .create(this)
            .build()
            .inject(this)
    }
}