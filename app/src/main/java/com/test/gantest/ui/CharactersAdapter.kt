package com.test.gantest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.gantest.data.repository.CharacterEntity
import com.test.gantest.databinding.CharacterListItemBinding

class CharactersAdapter(private val onCharacterClickListener: (character: CharacterEntity) -> Unit) :
ListAdapter<CharacterEntity, RecyclerView.ViewHolder>(BreakingBadCharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onCharacterClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val venue = getItem(position)
        (holder as CharacterViewHolder).bind(venue)
    }

    class CharacterViewHolder(
        private val binding: CharacterListItemBinding,
        private val onCharacterClickListener: (character: CharacterEntity) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.character?.let {
                    onCharacterClickListener(it)
                }
            }
        }

        fun bind(item: CharacterEntity) {
            binding.apply {
                character = item
                executePendingBindings()
            }
        }

    }

}

private class BreakingBadCharacterDiffCallback : DiffUtil.ItemCallback<CharacterEntity>() {

    override fun areItemsTheSame(
        oldItem: CharacterEntity,
        newItem: CharacterEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterEntity,
        newItem: CharacterEntity
    ): Boolean {
        return oldItem == newItem
    }

}