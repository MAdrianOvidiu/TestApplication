package com.test.gantest.di.character

import com.test.gantest.ui.CharacterListActivity
import dagger.Subcomponent

@CharacterScope
@Subcomponent(modules = [CharacterModule::class])
interface CharacterSubComponent{
    fun inject(activity: CharacterListActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): CharacterSubComponent
    }
}