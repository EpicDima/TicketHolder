package com.dima.ticketholder.di.android

import com.dima.ticketholder.ui.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment
}