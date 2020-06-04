package com.dima.ticketholder.di.modules

import dagger.Module

@Module(
    includes = [
        TestViewModelModule::class,
        TestBuilderModule::class,
        TestTicketManagerModule::class,
        TestDaoModule::class,
        TestDatabaseModule::class
    ]
)
class TestModule