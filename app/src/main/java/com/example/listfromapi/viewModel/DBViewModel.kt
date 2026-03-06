package com.example.listfromapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listfromapi.data.DAO
import com.example.listfromapi.data.FavouriteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DataBaseViewModel(private val dao: DAO): ViewModel(){
    val favourites: StateFlow<List<FavouriteEntity>> = dao.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addToFavourite(id: Int, name: String) = viewModelScope.launch {
        dao.insertFavourite(FavouriteEntity(id = id, name = name))
    }

    fun removeFromFavourite(fav: FavouriteEntity) = viewModelScope.launch {
        dao.deleteFromFavourite(fav)
    }
}