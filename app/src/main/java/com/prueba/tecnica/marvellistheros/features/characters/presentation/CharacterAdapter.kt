package com.prueba.tecnica.marvellistheros.features.characters.presentation

import android.view.View
import coil.load
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseAdapter
import com.prueba.tecnica.marvellistheros.databinding.ListItemCharacterBinding
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character

class CharacterAdapter : BaseAdapter<Character>() {

    var clickListener: (characterId: Long) -> Unit = { }
    var favoriteListener: (character: Character) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_character

    override fun View.bindView(item: Character, viewHolder: ViewHolder) {
        val binding = ListItemCharacterBinding.bind(this)

        setOnClickListener { clickListener(item.id) }

        binding.characterName.text = item.name
        binding.characterImage.load(item.thumbnail.getUrl())
        binding.characterFavorite.setOnClickListener {
            favoriteListener(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}