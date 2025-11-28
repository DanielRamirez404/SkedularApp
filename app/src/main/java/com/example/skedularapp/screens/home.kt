package com.example.skedularapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skedularapp.components.Header
import com.example.skedularapp.components.HomeworkCard
import com.example.skedularapp.components.SegmentedButtonWeek
import com.example.skedularapp.ui.theme.SkedularAppTheme
import java.util.Date

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController? = null, username: String = "user") {
    Scaffold {
 innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Header(
                username = username,
                onSettingsClick = {
                    navController?.navigate("options")
                },
                onAddEventClick = {
                    navController?.navigate("event")
                }
            )
            HomeworkList(modifier = Modifier.padding(top = 15.dp), navController = navController)
        }
    }
}

@Composable
fun HomeworkList(modifier: Modifier = Modifier, navController: NavController? = null) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SegmentedButtonWeek(date = java.sql.Date(Date().time))
        HomeworkCard(
            title = "Math Homework",
            subject = "Math",
            dueTime = "12:45",
            color = Color(0xFFE57373),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "History Reading",
            subject = "History",
            dueTime = "14:15",
            color = Color(0xFF81C784),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6),
            onClick = {
                navController?.navigate("event")
            }
        )
    }
}