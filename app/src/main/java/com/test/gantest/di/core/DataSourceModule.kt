package com.test.gantest.di.core

import com.test.gantest.data.api.BadService
import com.test.gantest.data.db.CharactersDao
import com.test.gantest.data.repository.datasource.CharacterCacheDataSource
import com.test.gantest.data.repository.datasource.CharacterLocalDataSource
import com.test.gantest.data.repository.datasource.CharacterRemoteDataSource
import com.test.gantest.data.repository.datasourceimpl.CharacterCacheDataSourceImpl
import com.test.gantest.data.repository.datasourceimpl.CharacterLocalDataSourceImpl
import com.test.gantest.data.repository.datasourceimpl.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//If we had multiple features I would split it into remote, local and cache per feature
@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(badService: BadService): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(badService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(charactersDao: CharactersDao): CharacterLocalDataSource {
        return CharacterLocalDataSourceImpl(charactersDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(): CharacterCacheDataSource {
        return CharacterCacheDataSourceImpl()
    }
}