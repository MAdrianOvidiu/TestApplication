package com.test.gantest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.domain.GetCharactersByNameUseCase
import com.test.gantest.domain.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {

    private val getCharactersUseCase: GetCharactersUseCase = mock()
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase = mock()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharacterViewModel


    @ExperimentalCoroutinesApi
    private fun unconfinifyTestScope() {
        ui = Dispatchers.Unconfined
        io = Dispatchers.Unconfined
        background = Dispatchers.Unconfined
    }

    @Before
    fun setUp() {
        unconfinifyTestScope()
        runBlocking {
            whenever(getCharactersUseCase.execute()).thenReturn(ALL_CHARACTERS)
            whenever(getCharactersByNameUseCase.execute(NAME_QUERY)).thenReturn(
                SEARCH_QUERIED_BY_NAME_CHARACTERS
            )
        }
        viewModel = CharacterViewModel(getCharactersUseCase, getCharactersByNameUseCase)
        viewModel.filteredDataObservable.observeForever {}
        viewModel.seasonsDataObservable.observeForever {}
    }

    @Test
    fun `filtered data initially returns all characters`() {
        assertThat(viewModel.filteredDataObservable.value).isEqualTo(ALL_CHARACTERS)
    }

    @Test
    fun `when search query is blank and series filter is blank then all characters returned`() {
        viewModel.searchByName("")
        viewModel.filterBySeason("")


        assertThat(viewModel.filteredDataObservable.value).isEqualTo(ALL_CHARACTERS)
    }

    @Test
    fun `when search query is blank and series filter is set then characters filtered by series returned`() {
        viewModel.searchByName("")
        viewModel.filterBySeason(SERIES_FILTER)

        assertThat(viewModel.filteredDataObservable.value).isEqualTo(FILTERED_BY_SERIES_CHARACTERS)
    }

    @Test
    fun `when search query is set and series filter is blank then characters by search returned`() {
        viewModel.searchByName(NAME_QUERY)
        viewModel.filterBySeason("")

        assertThat(viewModel.filteredDataObservable.value).isEqualTo(SEARCH_QUERIED_BY_NAME_CHARACTERS)
    }

    @Test
    fun `when search query is set and series filter is set then all characters by search and filter returned`() {
        viewModel.searchByName(NAME_QUERY)
        viewModel.filterBySeason(SERIES_FILTER)

        assertThat(viewModel.filteredDataObservable.value).isEqualTo(
            SEARCH_QUERIED_BY_NAME_AND_FILTERED_BY_SERIES_CHARACTERS
        )
    }

    companion object {
        const val NAME_QUERY = "Da"
        const val SERIES_FILTER = "4"

        val ALL_CHARACTERS = listOf(
            CharacterEntity(0, "Tim", "", "", "", "", "", listOf("1", "2", "3", "4")),
            CharacterEntity(1, "Dan", "", "", "", "", "", listOf("1", "2", "3")),
            CharacterEntity(2, "Dave", "", "", "", "", "", listOf("1", "2", "3", "4"))
        )
        val SEARCH_QUERIED_BY_NAME_CHARACTERS = listOf(
            CharacterEntity(1, "Dan", "", "", "", "", "", listOf("1", "2", "3")),
            CharacterEntity(2, "Dave", "", "", "", "", "", listOf("1", "2", "3", "4"))
        )
        val FILTERED_BY_SERIES_CHARACTERS = listOf(
            CharacterEntity(0, "Tim", "", "", "", "", "", listOf("1", "2", "3", "4")),
            CharacterEntity(2, "Dave", "", "", "", "", "", listOf("1", "2", "3", "4"))
        )

        val SEARCH_QUERIED_BY_NAME_AND_FILTERED_BY_SERIES_CHARACTERS = listOf(
            CharacterEntity(2, "Dave", "", "", "", "", "", listOf("1", "2", "3", "4"))
        )

    }
}