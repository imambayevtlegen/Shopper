package com.example.shopper.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.User
import com.example.shopper.data.util.Resource
import com.example.shopper.domain.usecase.ProfileUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
): ViewModel() {

    val theUser : MutableLiveData<Resource<User>> = MutableLiveData()

    fun updateUser(id: Int, user: User) = viewModelScope.launch(IO) {
        theUser.postValue(Resource.Loading())
        val result = runCatching {
            profileUseCase.updateUser(id, user)
        }.fold(
            onSuccess = { apiResult ->
                theUser.postValue(apiResult)
            },
            onFailure = { e ->
                theUser.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
            }
        )
    }
}