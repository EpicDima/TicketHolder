package com.dima.ticketholder.di.modules

import com.dima.ticketholder.data.AppDatabase
import dagger.Module
import dagger.Provides
import dao.TicketDao
import javax.inject.Singleton

@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideTicketDao(database: AppDatabase): TicketDao = database.ticketDao()
}