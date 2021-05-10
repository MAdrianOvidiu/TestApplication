package com.test.gantest.data.repository.datasourceimpl

import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.data.db.CharactersDao
import com.test.gantest.data.repository.datasource.CharacterLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* Room queries are executed on a background thread
*  so we only need to use coroutines to save the character list to DB
*/
class CharacterLocalDataSourceImpl(private val charactersDao: CharactersDao) :
    CharacterLocalDataSource {
    override suspend fun getCharactersFromDB(): List<CharacterEntity> {
        return charactersDao.getAllCharacters()
    }

    override suspend fun saveCharactersToDB(characters: List<CharacterEntity>) {
        charactersDao.saveCharacters(characters)
    }

    override suspend fun getCharactersByName(name: String): List<CharacterEntity> {
        return charactersDao.searchByName(name)
    }
}