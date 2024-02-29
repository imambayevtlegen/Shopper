package com.example.shopper.domain.usecase

import android.util.Log
import com.example.shopper.data.model.Login
import com.example.shopper.data.model.LoginResponse
import com.example.shopper.data.model.Name
import com.example.shopper.data.model.User
import com.example.shopper.data.util.Resource
import com.example.shopper.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    // TODO no flow
    // TODO use runcatching
    suspend fun loginUser(username: String, password: String): Resource<LoginResponse> {
        return runCatching {
            val login = Login(username, password)
            val response = repository.loginUser(login)
            Log.i("AuthUseCase", "${response.data?.token}")
            response
        }.fold(
            onSuccess = { it },
            onFailure = { e ->
                when (e) {
                    is HttpException -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
                    }

                    is IOException -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error("Couldn't reach server. Check your internet connection.")
                    }
                    else -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error("An unexpected error occurred.")
                    }
                }
            }
        )

    }

    suspend fun registerUser(username: String, password: String): Resource<User> {
        return runCatching {
            val user = User(
                email = "new email",
                id = 10,
                name = Name(firstname = "new firstname", lastname = "new lastname"),
                password = password,
                username = username,
                v = 1
            )
            val response = repository.registerUser(user)
            response
        }.fold(
            onSuccess = { it },
            onFailure = { e ->
                when (e) {
                    is HttpException -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
                    }

                    is IOException -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error("Couldn't reach the server. Check your internet connection")
                    }

                    else -> {
                        Log.i("AuthUseCase", "${e.localizedMessage}")
                        Resource.Error("An unexpected error occurred")
                    }
                }
            }
        )
    }
}