package com.example.shopper.domain.usecase

import com.example.shopper.data.model.Category
import com.example.shopper.data.model.Shop
import com.example.shopper.data.util.Outcome
import com.example.shopper.domain.repository.ShopRepository
import retrofit2.Response
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getAllProducts(): Outcome<Shop> {
        return repository.getAllProducts()
    }

    suspend fun getAllCategories(): Outcome<Category> {
        return repository.getAllCategories()
    }

    suspend fun getCategoryProducts(category: String): Outcome<Shop> {
        return repository.getCategoryProducts(category)
    }
}