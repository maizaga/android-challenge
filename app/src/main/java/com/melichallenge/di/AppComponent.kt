package com.melichallenge.di

import android.app.Application
import com.deedles.technisyschallenge.di.AppModule
import com.melichallenge.MeLiApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *
 * Created by maizaga on 2019-06-05.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuildersModule::class])
interface AppComponent {

    fun inject(app: MeLiApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}