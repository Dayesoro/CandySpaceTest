package com.example.candyspacetest.data.repository

import com.example.candyspace.domain.entity.Users
import com.example.candyspacetest.common.DataState
import kotlinx.coroutines.flow.Flow


interface UserRepositoryContract {
  fun fetchUsers(): Flow<DataState<Users>>
}