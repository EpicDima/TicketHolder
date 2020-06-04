package com.dima.ticketholder.di

import android.app.Application
import com.dima.ticketholder.di.android.ActivityBuilderModule
import com.dima.ticketholder.di.android.FragmentBuilderModule
import com.dima.ticketholder.di.android.ViewModelModule
import com.dima.ticketholder.di.modules.PreferencesModule
import com.dima.ticketholder.di.modules.TestModule
import com.dima.ticketholder.ui.MainActivityTest
import com.dima.ticketholder.ui.MainActivityViewModelTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class,
        PreferencesModule::class,
        TestModule::class
    ]
)
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(mainActivityTest: MainActivityTest)

    fun inject(mainActivityViewModelTest: MainActivityViewModelTest)
}
