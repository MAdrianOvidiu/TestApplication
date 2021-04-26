package com.test.gantest.domain

import com.test.gantest.data.repository.CharacterEntity

interface GetCharactersUseCase {
    suspend fun execute(): List<CharacterEntity>
}

open class GetCharactersUseCaseImpl(private val charactersRepository: CharactersRepository) :
    GetCharactersUseCase {
    override suspend fun execute(): List<CharacterEntity> = charactersRepository.getCharacters()
}