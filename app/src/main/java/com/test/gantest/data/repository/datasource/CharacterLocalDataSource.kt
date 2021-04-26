package com.test.gantest.data.repository.datasource

import com.test.gantest.data.repository.CharacterEntity

interface CharacterLocalDataSource {
    suspend fun getCharactersFromDB(): List<CharacterEntity>

    suspend fun saveCharactersToDB(characters: List<CharacterEntity>)

    suspend fun getCharactersByName(name: String): List<CharacterEntity>
}