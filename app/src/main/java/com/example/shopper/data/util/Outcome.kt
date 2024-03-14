package com.example.shopper.data.util

// TODO NO resource
// TODO kotlin result
// TODO re

sealed class Outcome<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Outcome<T>(data)
    class Loading<T>(data: T? = null) : Outcome<T>(data)
    class Error<T>(message: String, data: T? = null) : Outcome<T>(data, message)

}
//    inline fun <R> fold(
//        onSuccess: (T?) -> R,
//        onFailure: (String?) -> R
//    ): R = when (this) {
//        is Success -> onSuccess(data)
//        is Error -> onFailure(message)
//    }



//sealed class Outcome {
//    data class Success<T>(val data: T) : Outcome
//    data object Loading : Outcome
//    data class Error(val message: String) : Outcome
//}
//