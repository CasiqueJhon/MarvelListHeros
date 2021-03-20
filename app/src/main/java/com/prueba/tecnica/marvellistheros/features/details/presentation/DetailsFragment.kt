package com.prueba.tecnica.marvellistheros.features.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prueba.tecnica.marvellistheros.core.extension.viewBinding
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseFragment
import com.prueba.tecnica.marvellistheros.databinding.FragmentDetailsBinding
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail

@Suppress("TooManyFunctions")
class DetailsFragment: BaseFragment() {

    var viewModel: DetailsViewModel? = null

    private var binding by viewBinding<FragmentDetailsBinding>()

    private var isFirstPageComics = true
    private var isFirstPageSeries = true
    private var offsetComics = 0
    private var offsetSeries = 0
    private var isLoadingComics = false
    private var isLoadingSeries = false
    private var comicsAdapter = DetailsInfoAdapter()
    private var seriesAdapter = DetailsInfoAdapter()

    private var characterId = 0L
    private var characterDetail: CharacterDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterId = arguments?.let { DetailsFragmentArgs.fromBundle(it).characterId } ?: 0L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setScreen()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}