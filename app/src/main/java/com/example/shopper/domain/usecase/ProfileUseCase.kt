package com.example.shopper.domain.usecase

import com.example.shopper.data.model.User
import com.example.shopper.data.util.Resource
import com.example.shopper.domain.repository.ShopRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getUser(id: Int): Resource<User>{
        return repository.getUser(id)
    }

    suspend fun updateUser(id: Int, user: User): Resource<User>{
        return repository.updateUser(id, user)
    }
}