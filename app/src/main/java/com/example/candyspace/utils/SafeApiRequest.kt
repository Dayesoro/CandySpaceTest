package com.example.candyspacetest.common

import com.example.candyspace.common.DataState
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T>getResponse(call:suspend ()->Response<T>):DataState<T>{
        val result  = call.invoke()
        return if (result.isSuccessful){
            DataState.Success(result.body())
        }else if (!result.isSuccessful){
            val errorResponse = result.errorBody().toString()
            val data  = JSONObject(errorResponse).getString("error")
            DataState.Error(data)
        }else{
            DataState.Error(result.message())
        }
    }
}
