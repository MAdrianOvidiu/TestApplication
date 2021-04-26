package com.test.gantest.data.api

import com.test.gantest.data.repository.CharacterEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CharacterDtoTest {
    @Test
    fun `conversion to entities handles null inputs`() {
        val dto = CharacterDto(
            characterId = 0,
            characterName = null,
            actorName = null,
            occupation = null,
            img = null,
            status = null,
            nickname = null,
            seasonAppearance = null
        )

        val entities = listOf(dto).asEntities()

        assertThat(entities).first().isEqualTo(
            CharacterEntity(
                id = 0,
                characterName = "",
                actorName = "",
                img = "",
                occupation = "",
                status = "",
                nickname = "",
                seasonAppearance = listOf()
            )
        )
    }

    @Test
    fun `conversion parses dto to entities`() {
        val dto = CharacterDto(
            characterId = 1,
            characterName = "name",
            actorName = "actor name",
            occupation = listOf("1", "2", "3"),
            img = "http://img.com",
            status = "status",
            nickname = "nickname",
            seasonAppearance = listOf("1", "2", "4")
        )

        val entities = listOf(dto).asEntities()

        assertThat(entities).first().isEqualTo(
            CharacterEntity(
                id = 1,
                characterName = "name",
                actorName = "actor name",
                img = "http://img.com",
                occupation = "1,2,3",
                status = "status",
                nickname = "nickname",
                seasonAppearance = listOf("1", "2", "4")
            )
        )
    }
}