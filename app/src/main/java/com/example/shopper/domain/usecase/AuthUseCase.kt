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
    fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val login = Login(username, password)
            val response = repository.loginUser(login)
            Log.i("AuthUseCase", "I dey here, ${response.data?.token}")
            emit(response)
        } catch (e : HttpException){
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e : IOException){
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun registerUser(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val user = User(email = "new email", id = 10, name = Name(firstname = "new firstname", lastname = "new lastname"), password = password, username = username, v = 1)
        try {
            val response = repository.registerUser(user)
            emit(response)
        }   catch (e: HttpException){
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e : IOException){
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}