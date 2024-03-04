package com.example.shopper.data.repository.datasource

import com.example.shopper.data.model.*
import retrofit2.Response

interface  ShopRemoteDataSource {

    suspend fun getAllProducts(): Result<Shop>
    suspend fun getProduct(itemId: Int): Result<ShopItem>
    suspend fun getAllCategories(): Result<Category>
    suspend fun getCategoryProducts(category: String): Result<Shop>
    suspend fun uploadProduct(shopItem: ShopItem): Result<ShopItem>
    suspend fun updateProduct(id: Int, shopItem: ShopItem): Result<ShopItem>
    suspend fun deleteProduct(id: Int): Result<ShopItem>
    suspend fun getCart(id: Int): Result<Cart>
    suspend fun getCartProducts(id: Int): Result<CartItem>
    suspend fun addToCart(cartItem: CartItem): Result<CartItem>
    suspend fun updateCart(id: Int, cartItem: CartItem): Result<CartItem>
    suspend fun deleteCart(id: Int): Result<CartItem>
    suspend fun getUser(id: Int): Result<User>
    suspend fun updateUser(id: Int, user: User): Result<User>
    suspend fun loginUser(login: Login): Result<LoginResponse>
    suspend fun registerUser(user: User): Result<User>
}