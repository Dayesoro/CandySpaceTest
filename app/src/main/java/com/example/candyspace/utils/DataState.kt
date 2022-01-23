package com.example.candyspacetest.common

sealed class DataState<T>(
    val datas: T?=null
){
    class Loading <T> : DataState<T>()
    data class Success<T>(val data: T?):DataState<T>(datas = data)
    data class Error<T>(val error: String?):DataState<T>()
    class EmptyState<T>:DataState<T>()
}
