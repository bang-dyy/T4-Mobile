package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

    fun saveLoginSession(username: String, rememberMe: Boolean) {
        prefs.edit().apply {
            putBoolean("IS_LOGGED_IN", true)
            putString("USERNAME", username)
            putBoolean("REMEMBER_ME", rememberMe)
            apply()
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("IS_LOGGED_IN", false)

    fun isRememberMe(): Boolean = prefs.getBoolean("REMEMBER_ME", false)

    fun getUsername(): String = prefs.getString("USERNAME", "User") ?: "User"

    fun logout() {
        prefs.edit().apply {
            clear() // Menghapus semua data sesi
            apply()
        }
    }
}