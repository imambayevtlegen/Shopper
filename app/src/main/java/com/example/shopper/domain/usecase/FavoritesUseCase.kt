package com.example.shopper.domain.usecase

import androidx.lifecycle.LiveData
import com.example.shopper.data.model.ShopItem
import com.example.shopper.domain.repository.ShopRepository
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun addToFavorites(shopItem: ShopItem) {
        repository.addToFavorites(shopItem)
    }

    suspend fun deleteFavorites(shopItem: ShopItem) {
        repository.deleteFavoriteItem(shopItem)
    }

    fun getFavorites(): LiveData<List<ShopItem>> {
        return repository.getFavoriteItems()
    }

    suspend fun clearFavorites() {
        return repository.clearFavorites()
    }
}