package com.example.shopper.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem

interface ShopLocalDataSource {

    suspend fun addToCart(cartItem2: CartItem2)
    fun getCartItems(): LiveData<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()
    suspend fun addToFavorites(shopItem: ShopItem)
    fun getFavoriteItems(): LiveData<List<ShopItem>>
    suspend fun deleteFavoriteItem(shopItem: ShopItem)
    suspend fun clearFavorite()
}