package com.test.gantest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.gantest.domain.GetCharactersByNameUseCaseImpl
import com.test.gantest.domain.GetCharactersUseCaseImpl

class CharacterVMFactory(
    private val charactersUseCase: GetCharactersUseCaseImpl,
    private val charactersByNameUseCase: GetCharactersByNameUseCaseImpl
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(
            charactersUseCase,
            charactersByNameUseCase
            ) as T
    }
}