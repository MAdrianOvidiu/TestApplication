package com.test.gantest.domain

import com.test.gantest.data.repository.CharacterEntity

interface CharactersRepository {
    suspend fun getCharacters(): List<CharacterEntity>

    suspend fun getCharactersByName(name: String): List<CharacterEntity>
}