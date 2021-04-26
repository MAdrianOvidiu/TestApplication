package com.test.gantest.di.core

import com.test.gantest.di.character.CharacterSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        DataSourceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ])
interface AppComponent {
    fun characterSubcomponent(): CharacterSubComponent.Factory
}