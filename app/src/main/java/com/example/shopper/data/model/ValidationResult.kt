package com.example.shopper.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null,
)
