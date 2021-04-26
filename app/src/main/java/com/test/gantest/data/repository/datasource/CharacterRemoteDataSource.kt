package com.test.gantest.data.repository.datasource

import com.test.gantest.data.api.CharacterDto
import com.test.gantest.data.repository.CharacterEntity
import retrofit2.Response

interface CharacterRemoteDataSource {
    suspend fun getCharacters(): Response<List<CharacterDto>>
}