package com.example.shopper.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem


// TODO separate DAOS
@Dao
interface ShopDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cartItem2: CartItem2)

    @Update
    suspend fun updateCart(cartItem2: CartItem2)

    @Delete
    suspend fun deleteCart(cartItem2: CartItem2)

    @Query("select * from cart")
    fun cartItems(): LiveData<List<CartItem2>>

    @Query("delete from cart")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(shopItem: ShopItem)

    @Query("select * from favorites")
    fun getFavoriteItems(): LiveData<List<ShopItem>>

    @Delete
    suspend fun deleteFavorites(shopItem: ShopItem)

    @Query("delete from favorites")
    suspend fun clearFavorites()
}