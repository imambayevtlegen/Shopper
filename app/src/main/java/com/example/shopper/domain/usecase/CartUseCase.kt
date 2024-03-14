package com.example.shopper.domain.usecase

import androidx.lifecycle.LiveData
import com.example.shopper.data.model.CartItem2
import com.example.shopper.domain.repository.ShopRepository
import javax.inject.Inject

// TODO usecase is a single function class or interface
class CartUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun deleteCartItem(cartItem2: CartItem2) {
        repository.deleteCartItems(cartItem2)
    }

    suspend fun clearCart() {
        repository.clearCart()
    }

    fun getCartItems(): LiveData<List<CartItem2>> {
        return repository.getCartItems()
    }

    suspend fun addToCartItem(cartItem2: CartItem2) {
        repository.addToCartItems(cartItem2)
    }

    suspend fun updateCartItem(cartItem2: CartItem2) {
        repository.updateCartItems(cartItem2)
    }
}