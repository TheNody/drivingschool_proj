package com.example.drivingschool76.data.UIState

data class LoginUIState(

    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError :Boolean = false,
)