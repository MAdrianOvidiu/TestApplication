package com.test.gantest.data.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "breaking_bad_characters")
@Parcelize
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "character_id")val id: Int,
    @ColumnInfo(name = "character_name")val characterName: String,
    @ColumnInfo(name = "actor_name")val actorName: String,
    @ColumnInfo(name = "occupation")val occupation: String,
    @ColumnInfo(name = "image")val img: String,
    @ColumnInfo(name = "status")val status: String,
    @ColumnInfo(name = "nickname")val nickname: String,
    @ColumnInfo(name = "season_appearance")val seasonAppearance: List<String>
): Parcelable