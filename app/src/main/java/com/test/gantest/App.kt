package com.test.gantest

import android.app.Application
import com.test.gantest.di.Injector
import com.test.gantest.di.character.CharacterSubComponent
import com.test.gantest.di.core.AppComponent
import com.test.gantest.di.core.AppModule
import com.test.gantest.di.core.DaggerAppComponent
import com.test.gantest.di.core.NetworkModule

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        this.appComponent =
            DaggerAppComponent.builder().appModule(AppModule(applicationContext)).networkModule(
                NetworkModule(BuildConfig.BASE_URL)
            ).build()
    }

    override fun createCharacterSubcomponent(): CharacterSubComponent {
        return appComponent.characterSubcomponent().create()
    }

}