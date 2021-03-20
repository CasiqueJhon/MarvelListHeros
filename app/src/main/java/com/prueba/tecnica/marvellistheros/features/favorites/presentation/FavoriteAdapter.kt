package com.prueba.tecnica.marvellistheros.features.favorites.presentation

import android.view.View
import coil.load
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseAdapter
import com.prueba.tecnica.marvellistheros.databinding.ListItemFavoriteBinding
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite

class FavoriteAdapter : BaseAdapter<Favorite>() {

    var clickListener: (favoriteId: Long) -> Unit = { }
    var clickListenerFavorite: (favorite: Favorite) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_favorite

    override fun View.bindView(item: Favorite, viewHolder: ViewHolder) {
        val binding = ListItemFavoriteBinding.bind(this)

        setOnClickListener { clickListener(item.favoriteId) }

        binding.favoriteName.text = item.favoriteName
        binding.favoriteImage.load(item.favoriteUrl)
        binding.favoriteFavorite.setOnClickListener {
            clickListenerFavorite(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setIsRecyclable(false)
    }
}