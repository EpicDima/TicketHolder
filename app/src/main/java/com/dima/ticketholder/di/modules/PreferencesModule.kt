package com.dima.ticketholder.di.modules

import android.app.Application
import android.content.Context

import android.content.SharedPreferences
import dagger.Module

import dagger.Provides

import javax.inject.Singleton


@Module
class PreferencesModule {
    @Singleton
    @Provides
    fun providePreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
}
