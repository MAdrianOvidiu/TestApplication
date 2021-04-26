package com.test.gantest.di.core

import android.content.Context
import androidx.room.Room
import com.test.gantest.data.db.CharactersDao
import com.test.gantest.data.db.CharactersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCharactersDatabase(context: Context): CharactersDatabase{
        return Room.databaseBuilder(context, CharactersDatabase::class.java, "charactersclient")
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDAO(charactersDatabase: CharactersDatabase): CharactersDao{
        return charactersDatabase.charactersDao()
    }
}