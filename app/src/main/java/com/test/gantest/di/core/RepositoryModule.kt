package com.test.gantest.di.core

import com.test.gantest.domain.CharactersRepository
import com.test.gantest.data.repository.CharactersRepositoryImpl
import com.test.gantest.data.repository.datasource.CharacterCacheDataSource
import com.test.gantest.data.repository.datasource.CharacterLocalDataSource
import com.test.gantest.data.repository.datasource.CharacterRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        remoteDataSource: CharacterRemoteDataSource,
        localDataSource: CharacterLocalDataSource,
        cacheDataSource: CharacterCacheDataSource
    ): CharactersRepository{
        return CharactersRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            cacheDataSource = cacheDataSource
        )
    }
}