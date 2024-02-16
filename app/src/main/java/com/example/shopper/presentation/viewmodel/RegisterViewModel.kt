package com.example.shopper.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.util.Resource
import com.example.shopper.data.util.SharedPreference
import com.example.shopper.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefUtil: SharedPreference,
): ViewModel() {

    val successful : MutableLiveData<Boolean?> = MutableLiveData()
    val error : MutableLiveData<String?> = MutableLiveData()

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun registerUser(username: String, password: String){
        authUseCase.registerUser(username, password).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    Log.i("LoginViewModel", "Loading")
                }
                is Resource.Error -> {
                    Log.i("LoginViewModel", "${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    saveUserAccessToken(username)
                    Log.i("LoginViewModel", "${result.data.toString()}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigated(){
        successful.postValue(null)
        error.postValue(null)
    }
}