package com.test.gantest.di.core

import com.test.gantest.domain.CharactersRepository
import com.test.gantest.domain.GetCharactersByNameUseCaseImpl
import com.test.gantest.domain.GetCharactersUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCaseImpl {
        return GetCharactersUseCaseImpl(charactersRepository)
    }

    @Provides
    fun provideGetCharacterByNameUseCase(charactersRepository: CharactersRepository): GetCharactersByNameUseCaseImpl {
        return GetCharactersByNameUseCaseImpl(charactersRepository)
    }
}