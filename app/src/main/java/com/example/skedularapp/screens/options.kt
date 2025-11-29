package com.example.skedularapp.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skedularapp.components.DropDown
import com.example.skedularapp.components.FormScreen
import com.example.skedularapp.components.FormTextField
import com.example.skedularapp.components.Title

@Composable
fun OptionsScreen(onBack: () -> Unit) {
    FormScreen(
        title = "Options",
        onBack = onBack
    ) {
        val options = listOf("Light", "Dark", "System")
        var selectedTheme by remember { mutableStateOf(options[0]) }
        var username by remember { mutableStateOf("") }

        PreferencesSection(
            options = options,
            selectedTheme = selectedTheme,
            onThemeChange = { selectedTheme = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        PersonalInfoSection(
            username = username,
            onUsernameChange = { username = it }
        )
    }
}

@Composable
fun PreferencesSection(
    options: List<String>,
    selectedTheme: String,
    onThemeChange: (String) -> Unit = {}
) {
    Title("UI Preferences")
    Spacer(modifier = Modifier.height(24.dp))
    DropDown(
        icon = Icons.Filled.Build,
        title = "Theme",
        options = options,
        selectedOption = selectedTheme,
        onChange = onThemeChange
    )
}

@Composable
fun PersonalInfoSection(
    username: String,
    onUsernameChange: (String) -> Unit = {}
) {
    Title("Personal Info")
    Spacer(modifier = Modifier.height(16.dp))
    FormTextField(
        label = "Username",
        value = username,
        onValueChange = onUsernameChange
    )
}