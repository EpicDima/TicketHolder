package com.dima.ticketholder.di.modules

import android.app.Application
import androidx.room.Room
import com.dima.ticketholder.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase
            = AppDatabase.getInstance(application)
}