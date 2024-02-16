package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.User
import com.example.shopper.data.util.Resource
import com.example.shopper.data.util.SharedPreference
import com.example.shopper.domain.usecase.ProfileUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val sharedPreference: SharedPreference
): ViewModel() {

    val user : MutableLiveData<Resource<User>> = MutableLiveData()

    fun getUser(id: Int) = viewModelScope.launch(IO) {
        user.postValue(Resource.Loading())
        try {
            val apiResult = profileUseCase.getUser(id)
            user.postValue(apiResult)
        } catch (e: Exception){
            user.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }

    fun logoutUser() = viewModelScope.launch {
        sharedPreference.deleteAccessToken()
    }
}