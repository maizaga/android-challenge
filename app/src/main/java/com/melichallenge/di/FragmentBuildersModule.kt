package com.melichallenge.di

import com.melichallenge.ui.ItemFragment
import com.melichallenge.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * Created by maizaga on 2019-06-04.
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeItemFragment(): ItemFragment
}