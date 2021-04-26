package com.test.gantest.data.repository.datasourceimpl

import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.data.repository.datasource.CharacterCacheDataSource

class CharacterCacheDataSourceImpl: CharacterCacheDataSource {
    private var characterList = ArrayList<CharacterEntity>()

    override suspend fun saveCharactersToCache(characters: List<CharacterEntity>) {
        characterList.clear()
        characterList = ArrayList(characters)
    }

    override suspend fun getCharactersFromCache(): List<CharacterEntity> {
        return characterList
    }
}