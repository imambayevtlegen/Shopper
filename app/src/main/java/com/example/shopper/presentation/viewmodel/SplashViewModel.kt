package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopper.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    sharedPreference: SharedPreference
) : ViewModel() {

    val loggedIn: Boolean = sharedPreference.userIsLoggedIn()
}