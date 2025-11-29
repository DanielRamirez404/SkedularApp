package com.example.skedularapp.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.skedularapp.components.FormScreen

@Composable
fun OptionsScreen(onBack: () -> Unit) {
    FormScreen(
        title = "Options",
        onBack = onBack
    ) {
        Text("this is the settings screen")
    }
}