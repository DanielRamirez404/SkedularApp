package com.example.skedularapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skedularapp.screens.EventScreen
import com.example.skedularapp.screens.HomeScreen
import com.example.skedularapp.screens.OptionsScreen
import com.example.skedularapp.ui.theme.SkedularAppTheme
import com.example.skedularapp.utilities.DatabaseManager
import com.example.skedularapp.utilities.ThemePreference
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseManager.initialize(this)
        enableEdgeToEdge()
        setContent {

            val theme = remember { mutableStateOf(ThemePreference.SYSTEM) }

            SkedularAppTheme(darkTheme = theme.value == ThemePreference.DARK || (theme.value == ThemePreference.SYSTEM && isSystemInDarkTheme())) {

                val username = remember { mutableStateOf<String>("User") }

                val selectedEvent = remember { mutableStateOf<Int?>(null) }

                fun onSettingsChanged(newUsername : String, newTheme : String) {
                    theme.value = when (newTheme.lowercase(Locale.getDefault())) {
                        "light" -> ThemePreference.LIGHT
                        "dark" -> ThemePreference.DARK
                        else -> ThemePreference.SYSTEM
                    }

                    username.value = newUsername
                }

                LaunchedEffect(Unit) {
                    val savedSettings = DatabaseManager.getSettings()

                    onSettingsChanged(
                        savedSettings?.username ?: "User",
                        savedSettings?.theme ?: "system"
                    )
                }

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        HomeScreen(
                            username = username.value,
                            onGoToEventScreen = fun (id: Int?) {
                                selectedEvent.value = id
                                navController.navigate("event")
                            },
                            onSettingsClick = {
                                navController.navigate("options")
                            }
                        )
                    }

                    composable("options") {
                        val scope = rememberCoroutineScope()

                        OptionsScreen(
                            onBack = {
                                navController.popBackStack()
                            },

                            onDone = fun (newUsername, newTheme) {
                                onSettingsChanged(newUsername, newTheme)
                                scope.launch{
                                	DatabaseManager.updateSettings(newUsername, newTheme)
                                }
                            },

                            defaultUsername = username.value,

                            defaultTheme = when (theme.value) {
                                ThemePreference.LIGHT -> "light"
                                ThemePreference.DARK -> "dark"
                                else -> "system"
                            }
                        )
                    }

                    composable("event") {
                        val scope = rememberCoroutineScope()

                        EventScreen(
                            onBack = { navController.popBackStack() },
                            selectedEvent = selectedEvent.value,
                            onSave = fun (title, activity, subject, date, description) {
                                scope.launch {
                                    DatabaseManager.addEvent(title, activity, subject, date, description)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}