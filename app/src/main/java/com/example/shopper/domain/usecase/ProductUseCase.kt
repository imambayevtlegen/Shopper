package com.example.shopper.domain.usecase

import com.example.shopper.data.model.Category
import com.example.shopper.data.model.Shop
import com.example.shopper.data.util.Resource
import com.example.shopper.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getAllProducts(): Resource<Shop> {
        return repository.getAllProducts()
    }

    suspend fun getAllCategories(): Resource<Category> {
        return repository.getAllCategories()
    }

    suspend fun getCategoryProducts(category: String): Resource<Shop> {
        return repository.getCategoryProducts(category)
    }
}