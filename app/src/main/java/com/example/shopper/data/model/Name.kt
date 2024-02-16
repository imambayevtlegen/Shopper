package com.example.shopper.data.model

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String
)
