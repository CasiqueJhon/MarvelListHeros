package com.prueba.tecnica.marvellistheros.features.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetCharactersUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetListModeUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveListModeUseCase
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import kotlinx.coroutines.launch
import javax.inject.Inject

open class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val saveListModeUseCase: SaveListModeUseCase,
    private val getListModeUseCase: GetListModeUseCase
) : ViewModel() {

    open val characters: LiveData<List<Character>?> get() = _characters
    private val _characters = MutableLiveData<List<Character>?>()

    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open val savedFavorite: LiveData<Long?> get() = _savedFavorite
    private val _savedFavorite = MutableLiveData<Long?>()

    open val listMode: LiveData<Boolean?> get() = _listoMode
    private val _listoMode = MutableLiveData<Boolean?>()

    open val savedListMode: LiveData<Unit?> get() = _savedListMode
    private val _savedListMode = MutableLiveData<Unit?>()

    open fun getCharacters(apiKey: String, timestamp: Long, hash: String, offset: Int) {
        viewModelScope.launch {
            when (val response = getCharactersUseCase.invoke(apiKey, hash, timestamp, offset)) {
                is ResultWrapper.Success -> {
                    _characters.value = response.value
                }
                is ResultWrapper.GenericError -> {
                    _error.value = response.throwable
                }
                is ResultWrapper.NetworkError -> {
                    _error.value = response.throwable
                }
            }
        }
    }

    open fun favoriteCharacter(favorite: Favorite) {
        viewModelScope.launch {
            _savedFavorite.value = saveFavoriteUseCase(favorite)
        }
    }

    open fun savedListMode(listMode: Boolean) {
        viewModelScope.launch {
            _savedListMode.value = saveListModeUseCase(listMode)
        }
    }

    open fun getListMode() {
        viewModelScope.launch {
            _listoMode.value = getListModeUseCase()
        }
    }

}