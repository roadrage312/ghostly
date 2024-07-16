package com.ghostly.android.network.models

sealed class Result<out R> {
    class Success<out T>(val data: T?) : Result<T>()

    class Error(val errorCode: Int, val message: String? = null) : Result<Nothing>()
}
