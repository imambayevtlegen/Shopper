package com.example.shopper.data.repository.datasourceImpl

import androidx.lifecycle.LiveData
import com.example.shopper.data.db.ShopDAO
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem
import com.example.shopper.data.repository.datasource.ShopLocalDataSource
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopLocalDataSourceImpl @Inject constructor(
    private val shopDAO: ShopDAO
) : ShopLocalDataSource {

    override suspend fun addToCart(cartItem2: CartItem2) {
        return shopDAO.addToCart(cartItem2)
    }

    override fun getCartItems(): LiveData<List<CartItem2>> {
        return shopDAO.cartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return shopDAO.updateCart(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return shopDAO.deleteCart(cartItem2)
    }

    override suspend fun clearCart() {
        return shopDAO.clearAll()
    }

    override suspend fun addToFavorites(shopItem: ShopItem) {
        return shopDAO.addToFavorites(shopItem)
    }

    override fun getFavoriteItems(): LiveData<List<ShopItem>> {
        return shopDAO.getFavoriteItems()
    }

    override suspend fun deleteFavoriteItem(shopItem: ShopItem) {
        return shopDAO.deleteFavorites(shopItem)
    }

    override suspend fun clearFavorite() {
        return shopDAO.clearFavorites()
    }


}