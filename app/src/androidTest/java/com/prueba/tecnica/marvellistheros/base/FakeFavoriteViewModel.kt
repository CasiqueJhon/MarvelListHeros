package com.prueba.tecnica.marvellistheros.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prueba.tecnica.marvellistheros.base.DataProviderAndroidTest.createFavorite
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.GetFavoritesUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.GetListModeUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.SaveListModeUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.presentation.FavoriteViewModel
import kotlinx.coroutines.launch

open class FakeFavoriteViewModel(
    getFavoritesUseCase: GetFavoritesUseCase,
    deleteFavoriteUseCase: DeleteFavoriteUseCase,
    saveListModeUseCase: SaveListModeUseCase,
    getListModeUseCase: GetListModeUseCase,
    private val result: Result
) : FavoriteViewModel(
    getFavoritesUseCase,
    deleteFavoriteUseCase,
    saveListModeUseCase,
    getListModeUseCase
) {

    override val favorites: LiveData<List<Favorite>?> get() = _favorites
    private val _favorites = MutableLiveData<List<Favorite>?>()
    override val deleted: LiveData<Unit?> get() = _deleted
    private val _deleted = MutableLiveData<Unit?>()
    override val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    override val listMode: LiveData<Boolean?> get() = _listMode
    private val _listMode = MutableLiveData<Boolean?>()
    override val savedListMode: LiveData<Unit?> get() = _savedListMode
    private val _savedListMode = MutableLiveData<Unit?>()

    override fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = if (result == Result.SUCCESS) listOf(createFavorite()) else listOf()
        }
    }

    override fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            _deleted.value = Unit
        }
    }

    override fun saveListMode(listMode: Boolean) {
        viewModelScope.launch {
            _savedListMode.value = Unit
        }
    }

    override fun getListMode() {
        viewModelScope.launch {
            _listMode.value = true
        }
    }

    enum class Result {
        SUCCESS,
        ERROR
    }
}
