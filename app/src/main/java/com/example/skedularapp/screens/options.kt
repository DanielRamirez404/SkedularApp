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
import com.example.skedularapp.components.Title

@Composable
fun OptionsScreen(onBack: () -> Unit) {
    FormScreen(
        title = "Options",
        onBack = onBack
    ) {
        val options = listOf("Light", "Dark", "System")
        var selectedOption by remember { mutableStateOf(options[0]) }

        PreferencesSection(
            options = options,
            selectedOption = selectedOption,
            onChange = { selectedOption = it }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Title("Personal Info")
    }
}

@Composable
fun PreferencesSection(
    options: List<String>,
    selectedOption: String,
    onChange: (String) -> Unit = {}
) {
    Title("UI Preferences")
    Spacer(modifier = Modifier.height(24.dp))
    DropDown(
        icon = Icons.Filled.Build,
        title = "Theme",
        options = options,
        selectedOption = selectedOption,
        onChange = onChange
    )
}