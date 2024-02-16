package com.example.shopper.data.repository.datasource

import com.example.shopper.data.model.*
import retrofit2.Response

interface  ShopRemoteDataSource {

    suspend fun getAllProducts(): Response<Shop>
    suspend fun getProduct(itemId: Int): Response<ShopItem>
    suspend fun getAllCategories(): Response<Category>
    suspend fun getCategoryProducts(category: String): Response<Shop>
    suspend fun uploadProduct(shopItem: ShopItem): Response<ShopItem>
    suspend fun updateProduct(id: Int, shopItem: ShopItem): Response<ShopItem>
    suspend fun deleteProduct(id: Int): Response<ShopItem>
    suspend fun getCart(id: Int): Response<Cart>
    suspend fun getCartProducts(id: Int): Response<CartItem>
    suspend fun addToCart(cartItem: CartItem): Response<CartItem>
    suspend fun updateCart(id: Int, cartItem: CartItem): Response<CartItem>
    suspend fun deleteCart(id: Int): Response<CartItem>
    suspend fun getUser(id: Int): Response<User>
    suspend fun updateUser(id: Int, user: User): Response<User>
    suspend fun loginUser(login: Login): Response<LoginResponse>
    suspend fun registerUser(user: User): Response<User>
}