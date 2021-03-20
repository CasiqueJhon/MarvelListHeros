package com.prueba.tecnica.marvellistheros.features.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.core.extension.gone
import com.prueba.tecnica.marvellistheros.core.extension.viewBinding
import com.prueba.tecnica.marvellistheros.core.extension.visible
import com.prueba.tecnica.marvellistheros.core.plataform.Config
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseFragment
import com.prueba.tecnica.marvellistheros.databinding.FragmentDetailsBinding
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo
import com.prueba.tecnica.marvellistheros.features.details.domain.model.Thumbnail

@Suppress("TooManyFunctions")
class DetailsFragment : BaseFragment() {

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
        return FragmentDetailsBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.detailsViewModel()
        }

        setLayout()

        viewModel?.getDetails(
            characterId,
            Config.API_KEY,
            createTimestamp(),
            createHash(createTimestamp())
        )
        viewModel?.getDetailsComics(
            characterId,
            Config.API_KEY,
            createTimestamp(),
            createHash(createTimestamp()),
            offsetComics
        )
        viewModel?.getDetailsSeries(
            characterId,
            Config.API_KEY,
            createTimestamp(),
            createHash(createTimestamp()),
            offsetSeries
        )

        observableViewModel()
    }

    private fun setScreen() {
        setTitle("")
        showBackArrow()
        setHasOptionsMenu(false)
    }

    private fun setLayout() {
        binding.detailsFavorite.setOnClickListener {
            viewModel?.favoriteCharacter(
                Favorite(
                    characterDetail?.id ?: 0L,
                    characterDetail?.name ?: "",
                    characterDetail?.thumbnail?.getUrl(Thumbnail.ImageType.LANDSCAPE) ?: ""
                )
            )
        }
        binding.detailsCharacterComicsList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1) && !isLoadingComics) {
                    isLoadingComics = true

                    viewModel?.getDetailsComics(
                        characterId,
                        Config.API_KEY,
                        createTimestamp(),
                        createHash(createTimestamp()),
                        offsetComics
                    )

                    binding.detailsCharacterComicsListLoader.visible()
                    binding.detailsCharacterComicsList.scrollToPosition(comicsAdapter.itemCount - 1)
                }
            }
        })

        binding.detailsCharacterSeriesList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1) && !isLoadingSeries) {
                    isLoadingSeries = true

                    viewModel?.getDetailsSeries(
                        characterId,
                        Config.API_KEY,
                        createTimestamp(),
                        createHash(createTimestamp()),
                        offsetSeries
                    )

                    binding.detailsCharacterSeriesListLoader.visible()
                    binding.detailsCharacterSeriesList.scrollToPosition(seriesAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observableViewModel() {
        viewModel?.details?.observe(viewLifecycleOwner) {
            characterDetail = it

            setTitle(it?.name)
            binding.detailsFavorite.visible()
            binding.detailsCharacterDescription.text = if (it?.description?.isNotEmpty() == true) {
                it.description
            } else {
                getString(R.string.details_no_description)
            }
            binding.detailsCharacterImage.load(it?.thumbnail?.getUrl(Thumbnail.ImageType.LANDSCAPE))
        }
        viewModel?.comics?.observe(viewLifecycleOwner) {
            if (it?.size ?: 0 > 0) {
                binding.detailsCharacterComicsListLoader.gone()
                isLoadingComics = false

                offsetComics += it?.size ?: 0

                binding.detailsCharacterComics.visible()
                setComics(it)
            } else {
                binding.detailsCharacterComicsListLoader.gone()
            }
        }

        viewModel?.series?.observe(viewLifecycleOwner) {
            if (it?.size ?: 0 > 0) {
                binding.detailsCharacterSeriesListLoader.gone()
                isLoadingSeries = false

                offsetSeries += it?.size ?: 0

                binding.detailsCharacterSeries.visible()
                setSeries(it)
            } else {
                binding.detailsCharacterSeriesListLoader.gone()
            }
        }

        viewModel?.savedFavorite?.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.details_added_favorites),
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            showPlaceholder()
        }

        viewModel?.errorComics?.observe(viewLifecycleOwner) {
            showComicsPlaceholder()
        }

        viewModel?.errorSeries?.observe(viewLifecycleOwner) {
            showSeriesPlaceholder()
        }
    }

    private fun setComics(comics: List<DetailInfo>?) {
        comicsAdapter.setItems(comics)

        if (isFirstPageComics) {
            isFirstPageComics = false

            binding.detailsCharacterComicsList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                adapter = comicsAdapter
            }
        }
    }

    private fun setSeries(series: List<DetailInfo>?) {
        seriesAdapter.setItems(series)

        if (isFirstPageSeries) {
            isFirstPageSeries = false

            binding.detailsCharacterSeriesList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                adapter = seriesAdapter
            }
        }
    }


    private fun showPlaceholder() {
        binding.detailsPlaceholder.visible()
    }

    private fun showComicsPlaceholder() {
        if (!binding.detailsCharacterComicsList.isVisible) {
            binding.detailsCharacterComics.visible()
            binding.detailsComicsPlaceholder.visible()
        } else {
            binding.detailsCharacterComicsListLoader.gone()
        }
    }

    private fun showSeriesPlaceholder() {
        if (!binding.detailsCharacterSeriesList.isVisible) {
            binding.detailsCharacterSeries.visible()
            binding.detailsSeriesPlaceholder.visible()
        } else {
            binding.detailsCharacterSeriesListLoader.gone()
        }
    }
}