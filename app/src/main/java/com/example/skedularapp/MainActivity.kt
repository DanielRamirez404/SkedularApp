package com.example.skedularapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skedularapp.screens.HomeScreen
import com.example.skedularapp.screens.OptionsScreen
import com.example.skedularapp.screens.EventScreen
import com.example.skedularapp.ui.theme.SkedularAppTheme
import com.example.skedularapp.utilities.LocalSettings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkedularAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController = navController, username = LocalSettings.value.username)
                    }
                    composable("options") {
                        OptionsScreen(onBack = { navController.popBackStack() })
                    }
                    composable("event") {
                        EventScreen()
                    }
                }
            }
        }
    }
}
