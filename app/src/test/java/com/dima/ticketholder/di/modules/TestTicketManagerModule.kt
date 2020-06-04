package com.dima.ticketholder.di.modules

import com.dima.ticketholder.managers.TicketManager
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
class TestTicketManagerModule {

    @Singleton
    @Provides
    fun provideMockTicketManager(): TicketManager = mockk(relaxed = true)
}