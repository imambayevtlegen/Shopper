package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.*
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.util.Utils
import com.example.shopper.domain.usecase.CartUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    val totalItems: MutableLiveData<Int> = MutableLiveData()
    val totalItemsPrice: MutableLiveData<Double> = MutableLiveData()

    fun getCartItems() = liveData<List<CartItem2>> {
        val cartItems = cartUseCase.getCartItems()
        emitSource(cartItems)
        cartItems.value?.let { computeTotal(it) }

        val observer = Observer<List<CartItem2>> { items ->
            computeTotal(items)
        }
        cartItems.observeForever(observer)

    }

    fun computeTotal(cartItems: List<CartItem2>) = viewModelScope.launch(IO) {
        var price = 0.00
        cartItems.forEach { product ->
            price += product.price.toDouble()
        }
        totalItemsPrice.postValue(price)
        totalItems.postValue(cartItems.size)
    }

    fun deleteCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.deleteCartItem(cartItem2)
    }

    fun clearCart() = viewModelScope.launch(IO) {
        cartUseCase.clearCart()
    }

    fun increment(cartItem: CartItem2) {
        updateProductInCart(
            quantity = cartItem.quantity + 1,
            price = cartItem.price.toDouble() + cartItem.pricePerItem,
            cartItem
        )
    }

    fun decrement(cartItem: CartItem2) {
        if (cartItem.quantity > 1) {
            updateProductInCart(
                quantity = cartItem.quantity - 1,
                price = cartItem.price.toDouble() - cartItem.pricePerItem,
                cartItem
            )
        }
    }

    private fun updateProductInCart(quantity: Int, price: Double, cartItem: CartItem2) =
        viewModelScope.launch(IO) {
            val copy =
                cartItem.copy(price = Utils.formatPrice(price.toString()), quantity = quantity)
            cartUseCase.updateCartItem(copy)
        }
}