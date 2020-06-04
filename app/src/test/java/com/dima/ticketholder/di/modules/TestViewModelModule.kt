package com.dima.ticketholder.di.modules

import com.dima.ticketholder.ui.MainActivityViewModel
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
class TestViewModelModule {

    @Singleton
    @Provides
    fun provideMockMainActivityViewModel(): MainActivityViewModel = mockk(relaxed = true)
}