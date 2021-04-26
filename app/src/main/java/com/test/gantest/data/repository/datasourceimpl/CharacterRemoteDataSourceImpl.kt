package com.test.gantest.data.repository.datasourceimpl

import com.test.gantest.data.api.BadService
import com.test.gantest.data.api.CharacterDto
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.data.repository.datasource.CharacterRemoteDataSource
import retrofit2.Response

class CharacterRemoteDataSourceImpl(private val badService: BadService): CharacterRemoteDataSource {

    override suspend fun getCharacters(): Response<List<CharacterDto>> = badService.getCharacters()
}