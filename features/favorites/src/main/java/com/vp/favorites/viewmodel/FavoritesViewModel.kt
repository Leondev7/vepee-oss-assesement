package com.vp.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vp.core.storage.dao.MovieDao
import com.vp.favorites.extension.toFavoriteItem
import com.vp.favorites.model.FavoritesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject
constructor(
    private val movieDao: MovieDao
) : ViewModel() {

    private val favourites: MutableLiveData<List<FavoritesItem>> = MutableLiveData()
    private val loadingState: MutableLiveData<FavoritesState> = MutableLiveData()

    fun favourites(): LiveData<List<FavoritesItem>> = favourites

    fun state(): LiveData<FavoritesState> = loadingState

    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            loadingState.postValue(FavoritesState.IN_PROGRESS)
            movieDao.getAll().flowOn(Dispatchers.IO).collect { movies ->
                loadingState.postValue(FavoritesState.LOADED)
                favourites.postValue(movies.map { it.toFavoriteItem() })
            }
        }
    }

}