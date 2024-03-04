package com.example.shopper.data.repository.datasourceImpl

import com.example.shopper.data.api.ShopApiService
import com.example.shopper.data.model.*
import com.example.shopper.data.repository.datasource.ShopRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ShopRemoteDataSourceImpl @Inject constructor(
    private val apiService: ShopApiService
) : ShopRemoteDataSource {

    override suspend fun getAllProducts(): Result<Shop> {
        return apiService.getAllProducts()
    }

    override suspend fun getProduct(itemId: Int): Result<ShopItem> {
        return apiService.getProduct(itemId)
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Result<ShopItem> {
        return apiService.updateProduct(id, shopItem)
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Result<ShopItem> {
        return apiService.uploadProduct(shopItem)
    }

    override suspend fun getAllCategories(): Result<Category> {
         return apiService.getAllCategories()
    }

    override suspend fun getCategoryProducts(category: String): Result<Shop> {
        return apiService.getCategoryProducts(category)
    }

    override suspend fun deleteProduct(id: Int): Result<ShopItem> {
        return apiService.deleteProduct(id)
    }

    override suspend fun getCart(id: Int): Result<Cart> {
        return apiService.getCart(id)
    }

    override suspend fun getCartProducts(id: Int): Result<CartItem> {
        return apiService.getCartProducts(id)
    }

    override suspend fun addToCart(cartItem: CartItem): Result<CartItem> {
        return apiService.addToCart(cartItem)
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Result<CartItem> {
        return apiService.updateCart(id, cartItem)
    }

    override suspend fun deleteCart(id: Int): Result<CartItem> {
        return apiService.deleteCart(id)
    }

    override suspend fun getUser(id: Int): Result<User> {
        return apiService.getUser(id)
    }

    override suspend fun updateUser(id: Int, user: User): Result<User> {
        return apiService.updateUser(id, user)
    }

    override suspend fun loginUser(login: Login): Result<LoginResponse> {
        return apiService.loginUser(login)
    }

    override suspend fun registerUser(user: User): Result<User> {
        return apiService.registerUser(user)
    }
}