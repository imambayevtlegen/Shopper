package com.example.shopper.data.api

import com.example.shopper.data.model.*
import retrofit2.Response
import retrofit2.http.*


// TODO divide apis
interface ShopApiService {
    @GET("/products")
    suspend fun getAllProducts(): Result<Shop>

    @GET("products/{id}")
    suspend fun getProduct(@Path(value = "id") itemId: Int): Result<ShopItem>

    @GET("products/categories")
    suspend fun getAllCategories(): Result<Category>

    @GET("products/categories/{category}")
    suspend fun getCategoryProducts(@Path(value = "category") category: String): Result<Shop>

    @POST("products")
    suspend fun uploadProduct(@Body shopItem: ShopItem): Result<ShopItem>

    @PATCH("products/{id}")
    suspend fun updateProduct(@Path(value = "id")id: Int, @Body shopItem: ShopItem): Result<ShopItem>

    // TODO handle response
    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path(value = "id")id: Int): Result<ShopItem>

    @GET("carts/user/{id}")
    suspend fun getCart(@Path(value = "id")id: Int): Result<Cart>

    @GET("carts/user/{id}")
    suspend fun getCartProducts(@Path(value = "id")id: Int): Result<CartItem>

    @POST("carts")
    suspend fun addToCart(@Body cartItem: CartItem): Result<CartItem>

    @PATCH("carts/{id}")
    suspend fun updateCart(@Path(value = "id")id: Int, @Body cartItem: CartItem): Result<CartItem>

    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path(value = "id")id: Int): Result<CartItem>

    @GET("users/{id}")
    suspend fun getUser(@Path(value = "id")id: Int): Result<User>

    @PUT("user/{id}")
    suspend fun updateUser(@Path(value = "id")id: Int, @Body user: User): Result<User>

    @POST("auth/login")
    suspend fun loginUser(@Body login: Login): Result<LoginResponse>

    @POST("users")
    suspend fun registerUser(@Body user: User): Result<User>
}