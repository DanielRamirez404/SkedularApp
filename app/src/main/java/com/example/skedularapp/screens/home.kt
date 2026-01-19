package com.example.skedularapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skedularapp.components.Header
import com.example.skedularapp.components.HomeworkCard
import com.example.skedularapp.components.SegmentedButtonWeek
import com.example.skedularapp.utilities.DatabaseManager
import com.example.skedularapp.utilities.Event
import com.example.skedularapp.utilities.toJavaDate
import com.example.skedularapp.utilities.toSQLDate
import com.example.skedularapp.utilities.toString
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username: String = "user",
    onGoToEventScreen: (Int?) -> Unit,
    onSettingsClick: () -> Unit = {}
) {
    val date = remember { mutableStateOf(Date()) }
    val events = remember { mutableStateOf(listOf<Event>()) }

    val error = remember { mutableStateOf("none") }

    LaunchedEffect(date.value) {
        try {
            events.value = DatabaseManager.getEvents(date.value)
        } catch (e: Exception) {
            error.value = e.message ?: "Unknown error"
        }
    }

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
                onSettingsClick = onSettingsClick,
                onAddEventClick = { onGoToEventScreen(null) },
                eventsNumber = events.value.size
            )

            HomeworkList(
                modifier = Modifier.padding(top = 15.dp),
                date = date.value,
                events = events.value,
                onWeekdayClick = {
                    date.value = it
                },
                onGoToEventScreen = onGoToEventScreen
            )
        }
    }
}

@Composable
fun HomeworkList(
    modifier: Modifier = Modifier,
    date: Date,
    onWeekdayClick: (Date) -> Unit = {},
    events: List<Event>,
    onGoToEventScreen: (Int?) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SegmentedButtonWeek(
            date = toSQLDate(date),
            onClick = onWeekdayClick
        )

        events.map {
            val date = it.date

            val string_date = toString(date) // format: yyyy-MM-dd hh:mm:ss

            val hours = string_date.substring(11, 13)
            val minutes = string_date.substring(14, 16)

            HomeworkCard(
                title = it.title,
                subject = it.subject,
                dueTime = "$hours:$minutes",
                color = Color(0xFF64B5F6),
                onClick = { onGoToEventScreen(it.id) },
            )
        }
    }
}