package com.example.shopper.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.util.Outcome
import com.example.shopper.data.util.SharedPreference
import com.example.shopper.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefUtil: SharedPreference,
) : ViewModel() {

    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            val result = authUseCase.registerUser(username, password)
            when (result) {
                is Outcome.Loading -> {
                    Log.i("LoginViewModel", "Loading")
                }

                is Outcome.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i("LoginViewModel", "${result.message}")
                }

                is Outcome.Success -> {
                    successful.postValue(true)
                    saveUserAccessToken(username)
                    Log.i("LoginViewModel", "${result.data.toString()}")
                }
            }
        }
    }

    fun navigated() {
        successful.postValue(null)
        error.postValue(null)
    }
}