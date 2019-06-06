package com.melichallenge.di

import com.melichallenge.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * Created by maizaga on 2019-06-04.
 */
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity
}