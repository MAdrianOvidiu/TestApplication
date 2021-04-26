package com.test.gantest.data.api

import com.google.gson.annotations.SerializedName
import com.test.gantest.data.repository.CharacterEntity

data class CharacterDto(
    @SerializedName("char_id")
    val characterId: Int,
    @SerializedName("name")
    val characterName: String?,
    @SerializedName("portrayed")
    val actorName: String?,
    @SerializedName("occupation")
    val occupation: List<String>?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("appearance")
    val seasonAppearance: List<String>?
)

fun List<CharacterDto>?.asEntities(): List<CharacterEntity> {
    return this?.map {
        CharacterEntity(
            id = it.characterId,
            characterName = it.characterName.orEmpty(),
            actorName = it.actorName.orEmpty(),
            img = it.img.orEmpty(),
            occupation = it.occupation.orEmpty().joinToString(separator = ","),
            status = it.status.orEmpty(),
            nickname = it.nickname.orEmpty(),
            seasonAppearance = it.seasonAppearance.orEmpty()
        )
    } ?: emptyList()
}
