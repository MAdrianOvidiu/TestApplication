package com.test.gantest.data.repository

import android.util.Log
import com.test.gantest.data.api.asEntities
import com.test.gantest.domain.CharactersRepository
import com.test.gantest.data.repository.datasource.CharacterCacheDataSource
import com.test.gantest.data.repository.datasource.CharacterLocalDataSource
import com.test.gantest.data.repository.datasource.CharacterRemoteDataSource

class CharactersRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource,
    private val cacheDataSource: CharacterCacheDataSource
) : CharactersRepository {

    private val TAG = CharactersRepositoryImpl::class.simpleName

    override suspend fun getCharacters(): List<CharacterEntity> {
        return getCharactersFromCacheDataSource()
    }

    override suspend fun getCharactersByName(name: String): List<CharacterEntity> {
        lateinit var characters: List<CharacterEntity>

        try {
            characters = localDataSource.getCharactersByName(name)
        } catch (e: Exception) {
            Log.d(TAG, "Local fetch by name failed, $e")
        }
        return characters
    }

    suspend fun getCharactersFromRemoteDataSource(): List<CharacterEntity> {
        lateinit var characterList: List<CharacterEntity>

        try {
            val response = remoteDataSource.getCharacters()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                characterList = body.asEntities()
            }
        } catch (e: Exception) {
            Log.d(TAG, "Remote fetch chars failed, $e")
        }
        return characterList
    }

    suspend fun getCharactersFromLocalDataSource(): List<CharacterEntity> {
        lateinit var characterList: List<CharacterEntity>

        try {
            characterList = localDataSource.getCharactersFromDB()
        } catch (e: Exception) {
            Log.d(TAG, "Local fetch chars failed, $e")
        }

        if (characterList.isNotEmpty()) {
            return characterList
        } else {
            characterList = getCharactersFromRemoteDataSource()
            localDataSource.saveCharactersToDB(characterList)
        }

        return characterList
    }

    suspend fun getCharactersFromCacheDataSource(): List<CharacterEntity> {
        lateinit var characterList: List<CharacterEntity>

        try {
            characterList = cacheDataSource.getCharactersFromCache()
        } catch (e: Exception) {
            Log.d(TAG, "Cache fetch chars failed, $e")
        }

        if (characterList.isNotEmpty()) {
            return characterList
        } else {
            characterList = getCharactersFromLocalDataSource()
            cacheDataSource.saveCharactersToCache(characterList)
        }

        return characterList
    }
}