package com.test.gantest.domain

import com.test.gantest.data.repository.CharacterEntity

interface GetCharactersByNameUseCase {
    suspend fun execute(searchCriteria: String): List<CharacterEntity>
}

open class GetCharactersByNameUseCaseImpl(private val charactersRepository: CharactersRepository) :
    GetCharactersByNameUseCase {
    override suspend fun execute(searchCriteria: String): List<CharacterEntity> = charactersRepository.getCharactersByName(searchCriteria)
}