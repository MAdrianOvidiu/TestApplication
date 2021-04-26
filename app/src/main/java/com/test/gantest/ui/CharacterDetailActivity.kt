package com.test.gantest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.gantest.R
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.databinding.CharacterDetailActivityBinding

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val character = intent?.extras?.get(EXTRA_CHARACTER) as CharacterEntity
        setupDataBindingUI(character)
    }

    private fun setupDataBindingUI(character: CharacterEntity) {
        DataBindingUtil.setContentView<CharacterDetailActivityBinding>(
            this,
            R.layout.character_detail_activity
        ).apply {
            this.character = character
            lifecycleOwner = this@CharacterDetailActivity
        }
    }

    companion object {
        const val EXTRA_CHARACTER = "CharacterDetailActivity.extraCharacter"
    }
}