package com.dima.ticketholder.di.modules

import com.dima.ticketholder.data.AppDatabase
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
class TestDatabaseModule {

    @Singleton
    @Provides
    fun provideMockDatabase(): AppDatabase = mockk(relaxed = true)
}