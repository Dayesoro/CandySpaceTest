package com.example.candyspacetest.data.repository

import com.example.candyspace.data.model.toUser
import com.example.candyspace.data.remote.StackExchangeRemoteDataSource
import com.example.candyspace.domain.entity.Users
import com.example.candyspacetest.common.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository constructor(private val dataSource: StackExchangeRemoteDataSource):UserRepositoryContract {
    override fun fetchUsers(): Flow<DataState<Users>>  = flow {
        emit(DataState.Loading())
        val response = dataSource.getUsers().datas?.toUser()
        emit(DataState.Success(response))
    }.flowOn(IO)
        .catch {
            emit(DataState.Error(it.message))
        }
}