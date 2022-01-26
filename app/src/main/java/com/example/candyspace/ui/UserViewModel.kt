package com.example.candyspacetest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candyspace.domain.entity.Users
import com.example.candyspacetest.common.DataState
import com.example.candyspacetest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) :ViewModel() {

    private var _userUiState = MutableStateFlow<DataState<Users>>(DataState.EmptyState())
    val userUiState: StateFlow<DataState<Users>> = _userUiState

    init {
        getUsers()
    }
    private fun getUsers(){
        userRepository.fetchUsers().onEach {
           _userUiState.value=it
        }.launchIn(viewModelScope)
    }

}