package com.example.shopper.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import javax.inject.Inject
import com.example.shopper.data.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



@ProvidedTypeConverter
class Converters @Inject constructor(
    private val gson: Gson
) {
    @TypeConverter
    fun fromRating(rating: Rating):String{
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(json: String): Rating{
        val type = object : TypeToken<Rating>() {}.type
        return gson.fromJson(json, type)
    }

}
