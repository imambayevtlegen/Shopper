package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.CartItem
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem
import com.example.shopper.domain.usecase.CartUseCase
import com.example.shopper.domain.usecase.FavoritesUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {

    fun saveToCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.addToCartItem(cartItem2)
    }

    fun addToFavorites(shopItem: ShopItem) = viewModelScope.launch(IO) {
        favoritesUseCase.addToFavorites(shopItem)
    }

    fun removeFromFavorites(shopItem: ShopItem) = viewModelScope.launch(IO) {
        favoritesUseCase.deleteFavorites(shopItem)
    }
}