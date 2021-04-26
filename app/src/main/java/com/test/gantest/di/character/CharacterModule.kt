package com.test.gantest.di.character

import com.test.gantest.domain.GetCharactersByNameUseCaseImpl
import com.test.gantest.domain.GetCharactersUseCaseImpl
import com.test.gantest.ui.CharacterVMFactory
import dagger.Module
import dagger.Provides

@Module
class CharacterModule {
    @CharacterScope
    @Provides
    fun provideCharacterVMFactory(
        getCharactersUseCase: GetCharactersUseCaseImpl,
        getCharactersByNameUseCase: GetCharactersByNameUseCaseImpl
    ): CharacterVMFactory {
        return CharacterVMFactory(charactersUseCase = getCharactersUseCase,
            charactersByNameUseCase = getCharactersByNameUseCase
        )
    }
}