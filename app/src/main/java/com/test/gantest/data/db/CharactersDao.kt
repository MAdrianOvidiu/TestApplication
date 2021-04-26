package com.test.gantest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.gantest.data.repository.CharacterEntity

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characterList: List<CharacterEntity>)

    @Query("SELECT * FROM breaking_bad_characters")
    suspend fun getAllCharacters() : List<CharacterEntity>

    @Query("SELECT * FROM breaking_bad_characters WHERE character_name LIKE '%' || :name || '%'")
    suspend fun searchByName(name: String): List<CharacterEntity>
}