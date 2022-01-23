package com.example.candyspace.data.remote

import com.example.candyspace.data.Constant.ORDER
import com.example.candyspace.data.Constant.PAGE
import com.example.candyspace.data.Constant.PAGE_SIZE
import com.example.candyspace.data.Constant.SITE
import com.example.candyspace.data.Constant.SORT
import com.example.candyspace.data.model.UsersDTO
import com.example.candyspacetest.common.DataState
import com.example.candyspacetest.common.SafeApiRequest
import javax.inject.Inject

class StackExchangeRemoteDataSource @Inject constructor(
   private val api: StackExchangeApi
): SafeApiRequest() {

  suspend fun getUsers():DataState<UsersDTO>{
      return getResponse { api.getUser(PAGE, PAGE_SIZE, ORDER, SORT, SITE) }
  }

}