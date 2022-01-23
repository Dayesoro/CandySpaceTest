package com.example.candyspacetest.common

import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T>getResponse(call:suspend ()->Response<T>):DataState<T>{
        return try {
            val result  = call.invoke()
            return if (result.isSuccessful){
                DataState.Success(result.body())
            }else{
                val errorResponse = result.errorBody().toString()
                val data  = JSONObject(errorResponse).getString("error")
                DataState.Error(data)
            }
        }catch (e:Exception){
            DataState.Error(e.message?:"")
        }
    }
}