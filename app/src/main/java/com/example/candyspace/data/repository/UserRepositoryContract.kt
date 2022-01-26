package com.example.candyspace.data.repository

import com.example.candyspace.common.DataState
import com.example.candyspace.domain.entity.Users
import kotlinx.coroutines.flow.Flow


interface UserRepositoryContract {
  fun fetchUsers(): Flow<DataState<Users>>
}