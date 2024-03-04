package com.example.shopper.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.Category
import com.example.shopper.data.model.Shop
import com.example.shopper.data.util.Network.isNetworkAvailable
import com.example.shopper.data.util.Outcome
import com.example.shopper.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val productUseCase: ProductUseCase
) : AndroidViewModel(app) {

    val products: MutableLiveData<Outcome<Shop>> = MutableLiveData()
    val categories: MutableLiveData<Outcome<Category>> = MutableLiveData()

    fun getAllCategories() = viewModelScope.launch(IO) {
        categories.postValue(Outcome.Loading())
        val result = runCatching {
            if (isNetworkAvailable(app)) {
                productUseCase.getAllCategories()
            } else {
                Outcome.Error(message = "Internet not available")
            }
        }.fold(
            onSuccess = { apiResults ->
                categories.postValue(apiResults)
            },
            onFailure = { e ->
                categories.postValue(Outcome.Error(message = "Unknown error"))
            }
        )
    }


    fun getAllProducts() = viewModelScope.launch(IO) {
        products.postValue(Outcome.Loading())
        val result = runCatching {
            if (isNetworkAvailable(app)) {
                productUseCase.getAllProducts()
            } else {
                Outcome.Error(message = "Internet not available")
            }
        }
        result.fold(
            onSuccess = { apiResults ->
                products.postValue(apiResults)
            },
            onFailure = { e ->
                products.postValue(Outcome.Error(message = e.localizedMessage ?: "Unknown error"))
            }
        )
    }

    fun getCategoryProducts(category: String) = viewModelScope.launch(IO) {
        if (category != "All") {
            products.postValue(Outcome.Loading())
            val result = runCatching {
                if (isNetworkAvailable(app)) {
                    productUseCase.getCategoryProducts(category)
                } else {
                    Outcome.Error(message = "Internet not available")
                }
            }
            result.fold(
                onSuccess = { apiResults ->
                    products.postValue(apiResults)
                },
                onFailure = { e ->
                    products.postValue(Outcome.Error(message = e.localizedMessage ?: "Unknown error"))
                }
            )
        } else {
            getAllProducts()
        }
    }
}