package com.dima.ticketholder.di.modules

import com.dima.ticketholder.ui.MainActivityTest
import com.dima.ticketholder.ui.MainActivityViewModelTest
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class TestBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivityTest(): MainActivityTest

    @ContributesAndroidInjector
    abstract fun bindMainActivityViewModelTest(): MainActivityViewModelTest
}