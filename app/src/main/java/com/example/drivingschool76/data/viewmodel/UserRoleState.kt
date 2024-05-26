package com.example.drivingschool76.data.viewmodel

sealed interface UserRoleState {
    data object Unloggining: UserRoleState
    data object User: UserRoleState
    data object Instructor: UserRoleState
    data object Manager: UserRoleState
    data object Authenticated: UserRoleState
}
