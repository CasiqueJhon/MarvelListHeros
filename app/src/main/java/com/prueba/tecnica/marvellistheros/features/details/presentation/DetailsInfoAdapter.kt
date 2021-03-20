package com.prueba.tecnica.marvellistheros.features.details.presentation

import android.view.View
import coil.load
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseAdapter
import com.prueba.tecnica.marvellistheros.databinding.ListItemDetailsInfoBinding
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo
import com.prueba.tecnica.marvellistheros.features.details.domain.model.Thumbnail

class DetailsInfoAdapter: BaseAdapter<DetailInfo>() {
    override fun getLayoutRes(): Int = R.layout.list_item_details_info

    override fun View.bindView(item: DetailInfo, viewHolder: ViewHolder) {
       val binding = ListItemDetailsInfoBinding.bind(this)

        binding.detailInfoName.text = item.title
        binding.detailInfoImage.load(item.thumbnail.getUrl(Thumbnail.ImageType.PORTRAIT))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setIsRecyclable(false)
    }
}