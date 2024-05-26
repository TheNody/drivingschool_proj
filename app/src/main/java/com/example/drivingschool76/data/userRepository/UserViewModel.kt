package com.example.drivingschool76.data.userRepository

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivingschool76.data.network.SupabaseClient.client
import com.example.drivingschool76.data.userRepository.model.UserInfo
import com.example.drivingschool76.utils.AuthTokenManager
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {

    private val _userState = MutableStateFlow<UserInfo?>(null)
    val userState: StateFlow<UserInfo?> = _userState

    private val authTokenManager = AuthTokenManager(context)

    fun loadUser() {
        val id = authTokenManager.getUserUUID()

        if (id != null) {
            viewModelScope.launch {
                try {
                    val response = client.postgrest.from("public", "Users")
                        .select(
                            columns = Columns.list(
                                "firstName",
                                "lastName",
                                "email",
                                "surname",
                                "phonenumber",
                            )
                        ) {
                            filter {
                                eq("id", id)
                            }
                        }.decodeList<UserInfo>()

                    if (response.isNotEmpty()) {
                        val userData = response.firstOrNull()
                        if (userData != null) {
                            _userState.value = userData
                        }
                    } else {
                        Log.e("UserViewModel", "Пользователь не найден")
                    }
                } catch (e: Exception) {
                    Log.e("UserViewModel", "Исключение при загрузке пользователя: ${e.message}")
                }
            }
        } else {
            Log.e("UserViewModel", "UUID пользователя не найден в AuthTokenManager")
        }
    }
}