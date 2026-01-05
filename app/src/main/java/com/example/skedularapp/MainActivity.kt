package com.example.skedularapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skedularapp.screens.HomeScreen
import com.example.skedularapp.screens.OptionsScreen
import com.example.skedularapp.screens.EventScreen
import com.example.skedularapp.ui.theme.SkedularAppTheme
import com.example.skedularapp.utilities.DatabaseManager
import com.example.skedularapp.utilities.LocalSettings
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseManager.initialize(this)
        enableEdgeToEdge()
        setContent {
            SkedularAppTheme {

                val username = remember { mutableStateOf("") }

                LaunchedEffect(Unit) {
                    username.value = DatabaseManager.getUsername()
                }

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController = navController, username = username.value)
                    }
                    composable("options") {
                        OptionsScreen(onBack = { navController.popBackStack() })
                    }
                    composable("event") {
                        EventScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
