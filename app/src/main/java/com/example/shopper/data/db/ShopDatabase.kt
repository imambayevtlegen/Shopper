package com.example.shopper.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shopper.data.model.CartItem2
import com.example.shopper.data.model.ShopItem


// TODO row annotations
@Database(entities = [CartItem2::class, ShopItem::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDAO

}