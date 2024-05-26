package com.example.drivingschool76.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.drivingschool76.utils.UserInfoCar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {
    private val _userStateRole = MutableStateFlow<UserRoleState>(UserRoleState.User)
    val userStateRole: StateFlow<UserRoleState> = _userStateRole

    private val _users = MutableStateFlow<List<UserInfoCar>>(emptyList())
    val users: StateFlow<List<UserInfoCar>> = _users

    init {
        // Генерация тестовых данных
        _users.value = List(15) { index ->
            UserInfoCar(
                id = index.toString(),
                name = "User $index",
                avatarUrl = "https://placekitten.com/200/200?image=$index", // Placeholder URL
                remainingHours = (0..100).random(),
                phoneNumber = "+1-555-010$index"
            )
        }
    }
}

