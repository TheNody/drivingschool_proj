package com.example.drivingschool76.utils

import android.content.Context
import android.util.Log

class AuthTokenManager(context: Context) {

    private val sharedPref = SharedPreferenceHelper(context)

    companion object {
        private const val ACCESS_TOKEN_KEY = "accessToken"
        private const val SHARED_ROLE = "role"
        private const val USER_UUID_KEY = "userUuid"
        private const val TAG = "AuthTokenManager"
    }

    fun saveToken(token: String?) {
        token?.let {
            sharedPref.saveStringData(ACCESS_TOKEN_KEY, it)
            Log.d(TAG, "Token saved: $it")
        } ?: run {
            sharedPref.saveStringData(ACCESS_TOKEN_KEY, "")
            Log.d(TAG, "Token saved as empty string")
        }
    }

    fun getToken(): String? {
        val token = sharedPref.getStringData(ACCESS_TOKEN_KEY)
        Log.d(TAG, "Retrieved token: $token")
        return token
    }

    fun clearToken() {
        sharedPref.removeData(ACCESS_TOKEN_KEY)
        sharedPref.removeData(USER_UUID_KEY)
        sharedPref.removeData(SHARED_ROLE)
        Log.d(TAG, "All tokens and user data cleared")
    }

    fun saveRole(role: String) {
        sharedPref.saveStringData(SHARED_ROLE, role)
        Log.d(TAG, "Role saved: $role")
    }

    fun getRole(): String? {
        val role = sharedPref.getStringData(SHARED_ROLE)
        Log.d(TAG, "Retrieved role: $role")
        return role
    }

    fun saveUserUUID(uuid: String?) {
        uuid?.let {
            sharedPref.saveStringData(USER_UUID_KEY, it)
            Log.d(TAG, "UUID saved: $it")
        } ?: run {
            sharedPref.saveStringData(USER_UUID_KEY, "")
            Log.d(TAG, "UUID saved as empty string")
        }
    }

    fun getUserUUID(): String? {
        val uuid = sharedPref.getStringData(USER_UUID_KEY)
        Log.d(TAG, "Retrieved UUID: $uuid")
        return uuid
    }
}

