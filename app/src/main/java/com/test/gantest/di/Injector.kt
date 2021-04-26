package com.test.gantest.di

import com.test.gantest.di.character.CharacterSubComponent

interface Injector {
    fun createCharacterSubcomponent(): CharacterSubComponent
}