package com.dima.ticketholder.di

import android.app.Application
import com.dima.ticketholder.App
import com.dima.ticketholder.di.modules.DaoModule
import com.dima.ticketholder.di.modules.DatabaseModule
import com.dima.ticketholder.di.modules.PreferencesModule
import com.dima.ticketholder.di.modules.TicketManagerModule
import com.dima.ticketholder.di.android.ActivityBuilderModule
import com.dima.ticketholder.di.android.FragmentBuilderModule
import com.dima.ticketholder.di.android.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    ViewModelModule::class,
    PreferencesModule::class,
    TicketManagerModule::class,
    DaoModule::class,
    DatabaseModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}