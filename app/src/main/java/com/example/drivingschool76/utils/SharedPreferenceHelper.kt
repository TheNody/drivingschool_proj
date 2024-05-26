package com.example.drivingschool76.utils

import android.content.Context

class SharedPreferenceHelper(private val context: Context) {
    companion object {
        private const val MY_PREF_KEY = "MY_PREF"
    }

    fun saveStringData(key: String, data: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getStringData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun removeData(key: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(key).apply()
    }

    fun saveSwitchState(context: Context, key: String, value: Boolean) {
        val sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun getSwitchState(context: Context, key: String): Boolean {
        val sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }
}