package com.example.shopper.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.model.Category
import com.example.shopper.data.model.Shop
import com.example.shopper.data.util.Network
import com.example.shopper.data.util.Network.isNetworkAvailable
import com.example.shopper.data.util.Resource
import com.example.shopper.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val productUseCase: ProductUseCase
): AndroidViewModel(app) {

    val products: MutableLiveData<Resource<Shop>> = MutableLiveData()
    val categories: MutableLiveData<Resource<Category>> = MutableLiveData()

    fun getAllCategories() = viewModelScope.launch(IO){
        categories.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResults = productUseCase.getAllCategories()
                categories.postValue(apiResults)
            } else{
                categories.postValue(Resource.Error(message = "Internet not available"))
            }
        } catch (e: Exception){
            categories.postValue(Resource.Error(message = "${e.localizedMessage} ?: Unknown error."))
        }
    }


    fun getAllProducts() = viewModelScope.launch(IO){
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResults = productUseCase.getAllProducts()
                products.postValue(apiResults)
            } else{
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        } catch (e: Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getCategoryProducts(category: String) = viewModelScope.launch(IO){
        if(category != "All"){
            products.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)){
                    val apiResults = productUseCase.getCategoryProducts(category)
                    products.postValue(apiResults)
                } else{
                    products.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception){
                products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
            }
        } else{
            getAllProducts()
        }
    }
}