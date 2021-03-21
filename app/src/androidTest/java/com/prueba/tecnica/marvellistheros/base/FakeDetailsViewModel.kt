package com.prueba.tecnica.marvellistheros.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prueba.tecnica.marvellistheros.base.DataProviderAndroidTest.createCharacterDetail
import com.prueba.tecnica.marvellistheros.base.DataProviderAndroidTest.createDetailInfoAsList
import com.prueba.tecnica.marvellistheros.base.DataProviderAndroidTest.mockFavoriteId
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsComicsUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.SaveFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.details.presentation.DetailsViewModel
import kotlinx.coroutines.launch

open class FakeDetailsViewModel(
    getDetailsUseCase: GetDetailsUseCase,
    getDetailsComicsUseCase: GetDetailsComicsUseCase,
    getDetailsSeriesUseCase: GetDetailsSeriesUseCase,
    saveFavoriteUseCase: SaveFavoriteUseCase,
    private val result: Result
) : DetailsViewModel(
    getDetailsUseCase,
    getDetailsComicsUseCase,
    getDetailsSeriesUseCase,
    saveFavoriteUseCase
) {

    override val details: LiveData<CharacterDetail?> get() = _details
    private val _details = MutableLiveData<CharacterDetail?>()
    override val comics: LiveData<List<DetailInfo>?> get() = _comics
    private val _comics = MutableLiveData<List<DetailInfo>?>()
    override val series: LiveData<List<DetailInfo>?> get() = _series
    private val _series = MutableLiveData<List<DetailInfo>?>()
    override val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    override val errorComics: LiveData<Throwable> get() = _errorComics
    private val _errorComics = MutableLiveData<Throwable>()
    override val errorSeries: LiveData<Throwable> get() = _errorSeries
    private val _errorSeries = MutableLiveData<Throwable>()
    override val savedFavorite: LiveData<Long?> get() = _savedFavorite
    private val _savedFavorite = MutableLiveData<Long?>()

    override fun getDetails(characterId: Long, apikey: String, timestamp: Long, hash: String) {
        when (result) {
            Result.SUCCESS -> {
                _details.value = createCharacterDetail()
            }
            Result.NETWORK_ERROR -> {
                _error.value = Exception()
            }
            Result.GENERIC_ERROR -> {
                _error.value = Exception()
            }
        }
    }

    override fun getDetailsComics(characterId: Long, apikey: String, timestamp: Long, hash: String, offset: Int) {
        when (result) {
            Result.SUCCESS -> {
                _comics.value = createDetailInfoAsList()
            }
            Result.NETWORK_ERROR -> {
                _errorComics.value = Exception()
            }
            Result.GENERIC_ERROR -> {
                _errorComics.value = Exception()
            }
        }
    }

    override fun getDetailsSeries(characterId: Long, apikey: String, timestamp: Long, hash: String, offset: Int) {
        when (result) {
            Result.SUCCESS -> {
                _series.value = createDetailInfoAsList()
            }
            Result.NETWORK_ERROR -> {
                _errorSeries.value = Exception()
            }
            Result.GENERIC_ERROR -> {
                _errorSeries.value = Exception()
            }
        }
    }

    override fun favoriteCharacter(favorite: Favorite) {
        viewModelScope.launch {
            _savedFavorite.value = mockFavoriteId
        }
    }

    enum class Result {
        SUCCESS,
        NETWORK_ERROR,
        GENERIC_ERROR
    }
}