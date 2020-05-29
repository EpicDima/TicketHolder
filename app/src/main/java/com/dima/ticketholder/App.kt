package com.dima.ticketholder

import android.app.Application
import android.content.SharedPreferences
import com.dima.ticketholder.di.DaggerAppComponent
import com.dima.ticketholder.utils.setDayNightModeFromPreferences
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

        setDayNightModeFromPreferences(preferences)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
