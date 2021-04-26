package com.test.gantest.data.repository.datasource

import com.test.gantest.data.repository.CharacterEntity

interface CharacterCacheDataSource {

    suspend fun saveCharactersToCache(characters: List<CharacterEntity>)

    suspend fun getCharactersFromCache(): List<CharacterEntity>
}