package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE)

    var isDarkMode: Boolean
        get() = prefs.getBoolean("DARK_MODE", false)
        set(value) = prefs.edit().putBoolean("DARK_MODE", value).apply()

    var fontSize: Int
        get() = prefs.getInt("FONT_SIZE", 14)
        set(value) = prefs.edit().putInt("FONT_SIZE", value).apply()

    var isNotificationEnabled: Boolean
        get() = prefs.getBoolean("NOTIFICATION", true)
        set(value) = prefs.edit().putBoolean("NOTIFICATION", value).apply()
}