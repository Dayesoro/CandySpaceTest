package com.example.candyspace.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candyspace.common.DataState
import com.example.candyspace.data.repository.UserRepository
import com.example.candyspace.domain.entity.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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