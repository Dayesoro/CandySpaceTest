package com.example.candyspace.data.remote

import com.example.candyspace.data.model.UsersDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {
    @GET("users")
    suspend fun getUser(@Query("page")page:String,
                        @Query("pagesize")pageSize:String,
                        @Query("order")order:String,
                        @Query("sort")sort:String,
                        @Query("site")site:String,
    ): Response<UsersDTO>

}