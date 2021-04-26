package com.test.gantest.ui

import androidx.lifecycle.*
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.domain.GetCharactersByNameUseCase
import com.test.gantest.domain.GetCharactersUseCase

class CharacterViewModel(
    private val charactersUseCase: GetCharactersUseCase,
    private val charactersByNameUseCase: GetCharactersByNameUseCase
) : ViewModel() {
    var characterData: MutableLiveData<List<CharacterEntity>> = MutableLiveData()
    private val seasonQuery = MutableLiveData("")
    var seasonsData: MutableLiveData<List<String>> = MutableLiveData()

    /**
     * Filtering could have been done by creating another 2 use cases,
     * charactersBySeasonUseCase and charactersByNameAndSeasonUseCase.
     * I have chosen to do it this way because I wanted to play with transformations.
     */
    var filteredData = Transformations.switchMap(seasonQuery) { seasonQuery ->
        when {
            seasonsData.value?.contains(seasonQuery) ?: false -> filterDataBySeason(seasonQuery)
            else -> characterData
        }
    }

    init {
        getCharacters()
        getSeasons()
    }

    private fun getCharacters() {
        ioJob {
            characterData.postValue(charactersUseCase.execute())
        }
    }

    private fun getSearchedCharacters(name: String) {
        ioJob {
            characterData.postValue(charactersByNameUseCase.execute(name))
        }
    }

    fun filterBySeason(season: String) {
        seasonQuery.value = season
    }

    fun searchByName(name: String) {
        if (name.isNotBlank()) {
            getSearchedCharacters(name)
        } else {
            getCharacters()
        }
    }

    private fun getSeasons() {
        ioJob {
            seasonsData.postValue(
                charactersUseCase.execute().map { it.seasonAppearance }
                    ?.flatten()
                    ?.toSortedSet()
                    ?.filterNot { it.isNullOrBlank() }
            )
        }
    }


    private fun filterDataBySeason(seasonQuery: String) = characterData.map { list ->
        list.filter { char ->
            seasonQuery in char.seasonAppearance
        }
    }
}
