package com.example.skedularapp.utilities

import androidx.compose.runtime.mutableStateOf

data class UserSettings(
    val themePreference: ThemePreference = ThemePreference.LIGHT,
    val username: String = "User"
)

enum class ThemePreference {
    LIGHT,
    DARK,
    SYSTEM
}

val LocalSettings = mutableStateOf(UserSettings())
