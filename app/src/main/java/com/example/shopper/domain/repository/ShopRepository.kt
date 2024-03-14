package com.example.shopper.domain.repository

import androidx.lifecycle.LiveData
import com.example.shopper.data.model.Cart
import com.example.shopper.data.model.CartItem
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.Category
import com.example.shopper.data.model.Login
import com.example.shopper.data.model.LoginResponse
import com.example.shopper.data.model.Product
import com.example.shopper.data.model.Shop
import com.example.shopper.data.model.ShopItem
import com.example.shopper.data.model.User
import com.example.shopper.data.util.Outcome

interface ShopRepository {
    suspend fun getAllProducts(): Outcome<Shop>
    suspend fun getProduct(itemId: Int): Outcome<ShopItem>
    suspend fun getAllCategories(): Outcome<Category>
    suspend fun getCategoryProducts(category: String): Outcome<Shop>
    suspend fun uploadProduct(shopItem: ShopItem): Outcome<ShopItem>
    suspend fun updateProduct(id: Int, shopItem: ShopItem): Outcome<ShopItem>
    suspend fun deleteProduct(id: Int): Outcome<ShopItem>
    suspend fun getCart(id: Int): Outcome<Cart>
    suspend fun getCartProducts(id: Int): Outcome<List<Product>>
    suspend fun addToCart(cartItem: CartItem): Outcome<CartItem>
    suspend fun updateCart(id: Int, cartItem: CartItem): Outcome<CartItem>
    suspend fun deleteCart(id: Int): Outcome<CartItem>
    suspend fun getUser(id: Int): Outcome<User>
    suspend fun updateUser(id: Int, user: User): Outcome<User>
    suspend fun loginUser(login: Login): Outcome<LoginResponse>
    suspend fun registerUser(user: User): Outcome<User>

    suspend fun addToCartItems(cartItem2: CartItem2)
    fun getCartItems(): LiveData<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()

    suspend fun addToFavorites(shopItem: ShopItem)
    fun getFavoriteItems() : LiveData<List<ShopItem>>
    suspend fun deleteFavoriteItem(shopItem: ShopItem)
    suspend fun clearFavorites()
}