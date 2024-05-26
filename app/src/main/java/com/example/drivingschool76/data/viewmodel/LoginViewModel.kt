package com.example.drivingschool76.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivingschool76.data.UIEvent.LoginUIEvent
import com.example.drivingschool76.data.UIState.LoginUIState
import com.example.drivingschool76.data.network.SupabaseClient.client
import com.example.drivingschool76.data.rules.Validator
import com.example.drivingschool76.utils.AuthTokenManager
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val authTokenManager = AuthTokenManager(application.applicationContext)
    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationsPassed = mutableStateOf(false)

    private val _userStateRole = MutableStateFlow<UserRoleState>(UserRoleState.Unloggining)
    val userStateRole = _userStateRole.asStateFlow()

    init {
        reloadAll()
    }

    fun reloadAll() {
        viewModelScope.launch {
            val savedToken = authTokenManager.getToken()
            val savedRole = authTokenManager.getRole()
            val savedUUID = authTokenManager.getUserUUID()
            if (!savedToken.isNullOrBlank() && !savedUUID.isNullOrBlank()) {
                _userStateRole.value = setUserRole(savedRole)
            } else {
                _userStateRole.value = UserRoleState.Unloggining
            }
        }
    }

    private fun saveToken() {
        val accessToken = client.auth.currentAccessTokenOrNull()
        val role = client.auth.currentUserOrNull()?.role
        val uuid = client.auth.currentUserOrNull()?.id

        viewModelScope.launch {
            _userStateRole.value = setUserRole(role)
            authTokenManager.saveToken(accessToken)
            authTokenManager.saveRole(role ?: "")
            authTokenManager.saveUserUUID(uuid)
        }
    }

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(email = event.email)
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(password = event.password)
            }
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(loginUIState.value.email)
        val passwordResult = Validator.validatePassword(loginUIState.value.password)

        loginUIState.value = loginUIState.value.copy(
            emailError = !emailResult.status,
            passwordError = !passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        viewModelScope.launch {
            try {
                client.auth.signInWith(Email) {
                    email = loginUIState.value.email
                    password = loginUIState.value.password
                }.also {
                    saveToken()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Login error: ${e.localizedMessage}")
            }
        }
    }

    private fun setUserRole(savedRole: String?): UserRoleState {
        return when (savedRole?.lowercase()) {
            "authenticated" -> UserRoleState.Authenticated
            "user" -> UserRoleState.User
            "instructor" -> UserRoleState.Instructor
            "manager" -> UserRoleState.Manager
            else -> UserRoleState.Unloggining
        }
    }
}

