package com.example.candyspacetest.data.repository

import com.example.candyspacetest.common.DataState
import com.example.candyspacetest.domain.entity.Users
import kotlinx.coroutines.flow.Flow


interface UserRepositoryContract {
  fun fetchUsers(): Flow<DataState<Users>>
}