package com.dima.ticketholder.di.modules

import dagger.Module
import dagger.Provides
import dao.TicketDao
import io.mockk.mockk
import javax.inject.Singleton


@Module
class TestDaoModule {

    @Singleton
    @Provides
    fun provideMockTicketDao(): TicketDao = mockk(relaxed = true)
}