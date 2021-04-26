package com.test.gantest.di.core

import android.content.Context
import com.test.gantest.di.character.CharacterSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [CharacterSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context{
        return context.applicationContext
    }
}