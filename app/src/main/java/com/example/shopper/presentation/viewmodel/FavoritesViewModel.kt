package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.ShopItem
import com.example.shopper.domain.usecase.FavoritesUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase
): ViewModel() {

    fun getFavorites() = liveData {
        favoritesUseCase.getFavorites().collect{
            emit(it)
        }
    }

    fun deleteFavorites(shopItem: ShopItem) = viewModelScope.launch(IO) {
        favoritesUseCase.deleteFavorites(shopItem)
    }

    fun clearFavorites() = viewModelScope.launch(IO) {
        favoritesUseCase.clearFavorites()
    }
}