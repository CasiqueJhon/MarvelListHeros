package com.prueba.tecnica.marvellistheros.features.characters.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.core.extension.gone
import com.prueba.tecnica.marvellistheros.core.extension.viewBinding
import com.prueba.tecnica.marvellistheros.core.extension.visible
import com.prueba.tecnica.marvellistheros.core.plataform.Config
import com.prueba.tecnica.marvellistheros.core.plataform.base.BaseFragment
import com.prueba.tecnica.marvellistheros.databinding.FragmentCharactersBinding
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.main.MainFragmentDirections

@Suppress("TooManyFunctions")
class CharactersFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    var viewModel: CharactersViewModel? = null

    private var binding by viewBinding<FragmentCharactersBinding>()

    private var characterAdapter = CharacterAdapter()

    private var isFirstPage = true
    private var isLoading = false
    private var offset = 0
    private var contentAsList: Boolean? = null
    private var favoriteListener = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setScreen()

        return FragmentCharactersBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.charactersViewModel()
        }

        setLayout()

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        binding.charactersRefresh.setOnRefreshListener(this)

        isFirstPage = true

        viewModel?.getListMode()
        viewModel?.getCharacters(
            Config.API_KEY,
            createTimestamp(),
            createHash(createTimestamp()),
            offset
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (contentAsList != null) {
            if (contentAsList != true) {
                inflater.inflate(R.menu.menu_grid, menu)
            } else {
                inflater.inflate(R.menu.menu_list, menu)
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGrid, R.id.menuList -> {
                Config.refreshCharacter = true
                Config.refreshFavorite = true

                viewModel?.savedListMode(contentAsList != true)

                return true
            }
        }

        return false
    }

    override fun onRefresh() {
        offset = 0
        characterAdapter.clearItems()

        viewModel?.getCharacters(Config.API_KEY, createTimestamp(), createHash(createTimestamp()), offset)
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    private fun setLayout() {
        binding.charactersRefresh.setOnRefreshListener(this)

        binding.charactersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    isLoading = true

                    viewModel?.getCharacters(
                        Config.API_KEY,
                        createTimestamp(), createHash(createTimestamp()),
                        offset
                    )

                    binding.charactersListLoader.visible()
                    binding.charactersList.scrollToPosition(characterAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel?.characters?.observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                binding.charactersRefresh.isRefreshing = false

                offset += it.size

                isLoading = false

                showList()

                setList(it)
            } else {
                showPlaceHolder()
            }
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            binding.charactersRefresh.isRefreshing = false

            if (isLoading) {
                isLoading = false
            }

            showPlaceHolder()
        }

        viewModel?.savedFavorite?.observe(viewLifecycleOwner) {
            if (favoriteListener) {
                favoriteListener = false
                Toast.makeText(requireContext(), getString(R.string.characters_added_favorite),
                Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel?.listMode?.observe(viewLifecycleOwner) {
            contentAsList = it ?: false

            refreshList()

            activity?.invalidateOptionsMenu()
        }

        viewModel?.savedListMode?.observe(viewLifecycleOwner) {
            viewModel?.getListMode()
        }
    }

    private fun setList(characters: List<Character>) {
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { characterId ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = characterId
            navController?.navigate(action)
        }
        characterAdapter.favoriteListener = {
            viewModel?.favoriteCharacter(Favorite(it.id, it.name, it.thumbnail.getUrl()))
            favoriteListener = true
        }
        characterAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        if (isFirstPage && binding.charactersList.layoutManager == null) {
            isFirstPage = false

            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = if (contentAsList == true) {
                    GridLayoutManager(context, 2)
                } else {
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                adapter = characterAdapter
            }
        }
    }

    private fun refreshList() {
        if (binding.charactersList.layoutManager == null || Config.refreshCharacter) {
            Config.refreshCharacter = false
            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = if (contentAsList == true) {
                    GridLayoutManager(context, 2)
                } else {
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                adapter = characterAdapter
            }
        }
    }

    private fun showList() {
        binding.charactersList.visible()
        binding.charactersPlaceholder.gone()
        binding.charactersListLoader.gone()
        binding.charactersProgressBar.gone()

    }

    private fun showPlaceHolder() {
        if (!binding.charactersList.isVisible || isFirstPage) {
            binding.charactersPlaceholder.visible()
            binding.charactersListLoader.gone()
            binding.charactersProgressBar.gone()
            binding.charactersList.gone()
        } else {
            binding.charactersListLoader.gone()
        }
    }
}