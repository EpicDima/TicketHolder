package com.dima.ticketholder.di.modules

import com.dima.ticketholder.managers.TicketManager
import dagger.Module
import dagger.Provides
import dao.TicketDao
import javax.inject.Singleton

@Module
class TicketManagerModule {

    @Singleton
    @Provides
    fun provideTicketManager(ticketDao: TicketDao): TicketManager = TicketManager(ticketDao)
}