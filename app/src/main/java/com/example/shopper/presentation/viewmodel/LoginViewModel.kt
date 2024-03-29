package com.example.shopper.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.util.Outcome
import com.example.shopper.data.util.SharedPreference
import com.example.shopper.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefCase: SharedPreference,
) : ViewModel() {

    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    private fun saveUserAccessToken(token: String) = sharedPrefCase.saveUserAccessToken(token)

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val result = authUseCase.loginUser(username, password)
            when (result) {
                is Outcome.Loading -> {
                    Log.i("LoginViewModel", "Loading")
                }

                is Outcome.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i("LoginViewModel", "Error ${result.message}")
                }

                is Outcome.Success -> {
                    successful.postValue(true)
                    saveUserAccessToken("${result.data?.token}")
                    Log.i("LoginViewModel", "Success ${result.data?.token}")
                }
            }
        }
    }

    fun navigated() {
        successful.postValue(null)
        error.postValue(null)
    }
}