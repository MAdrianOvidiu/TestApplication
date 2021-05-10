package com.test.gantest.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.test.gantest.R
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.databinding.CharacterListActivityBinding
import com.test.gantest.di.Injector
import javax.inject.Inject

class CharacterListActivity : AppCompatActivity() {

    private val  viewModel by lazy {
        ViewModelProvider(this, factory).get(CharacterViewModel::class.java)
    }
    @Inject
    lateinit var factory: CharacterVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as Injector).createCharacterSubcomponent()
            .inject(this)

        setupDataBindingUI()
    }

    private fun setupDataBindingUI() {
        val binding = DataBindingUtil.setContentView<CharacterListActivityBinding>(
            this,
            R.layout.character_list_activity
        ).apply {
            lifecycleOwner = this@CharacterListActivity
        }

        setupListUI(binding.recyclerView)
        setupChipGroup(binding.seriesChips)
    }

    private fun setupListUI(recyclerView: RecyclerView) {
        val adapter = CharactersAdapter { character ->
            startDetailActivity(character)
        }
        recyclerView.adapter = adapter
        viewModel.filteredDataObservable.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun setupChipGroup(chipGroup: ChipGroup) {
        viewModel.seasonsDataObservable.observe(this, Observer {
            if (it != null) {
                chipGroup.removeAllViews()
                it.map { season ->
                    Chip(chipGroup.context).apply {
                        text = season
                        setChipDrawable(
                            ChipDrawable.createFromAttributes(
                                chipGroup.context,
                                null,
                                0,
                                R.style.Widget_MaterialComponents_Chip_Choice
                            )
                        )
                        isChecked = false
                    }
                }.forEach { chip ->
                    chipGroup.addView(chip)
                }

                chipGroup.setOnCheckedChangeListener { _, checkedId ->
                    viewModel.filterBySeason(checkedId.toString())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.characters_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.let { setupSearchUI(it) }

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchUI(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchByName(newText.orEmpty())
                return true
            }
        })
    }

    private fun startDetailActivity(character: CharacterEntity) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CharacterDetailActivity.EXTRA_CHARACTER, character)
        }
        startActivity(intent)
    }

}